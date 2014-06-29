package net.kiminotes.maven.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.manager.WagonManager;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.repository.ArtifactRepositoryFactory;
import org.apache.maven.artifact.repository.ArtifactRepositoryPolicy;
import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectBuilder;
import org.apache.maven.project.ProjectBuilderConfiguration;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Repository;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.util.DirectoryScanner;
import org.codehaus.plexus.util.IOUtil;


/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2014-06-23
 * @goal extract
 * @requiresProject false
 */
public class ExtractSourceMojo extends AbstractMojo {

    /**
     * @parameter expression="${extract.pom}"
     */
    private File pom;

    /**
     * @parameter expression="${output.directory}"
     */
    private File outputDirectory;

    /**
     * @parameter expression="${groupId}"
     * @required
     */
    private String groupId;
    /**
     * @parameter expression="${artifactId}"
     * @required
     */
    private String artifactId;
    /**
     * @parameter expression="${version}"
     * @required
     */
    private String version;

    /**
     * @component
     */
    private ArtifactFactory    artifactFactory;
    /**
     * @component
     */
    private WagonManager       wagonManager;
    /**
     * @parameter default-value="${localRepository}"
     */
    private ArtifactRepository localRepository;

    /**
     * @component
     */
    private MavenProjectBuilder mavenProjectBuilder;

    private ArtifactResolver artifactResolver;

    /**
     * @component
     */
    private ArtifactRepositoryFactory artifactRepositoryFactory;

    /**
     * @parameter default-value="${settings}"
     */
    protected Settings settings;

    private static final String FS = System.getProperty("file.separator");

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        if (outputDirectory == null) {
            outputDirectory = new File(artifactId);
        }

        try {
            outputDirectory = outputDirectory.getCanonicalFile();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        File java = new File(outputDirectory, "src" + FS + "main" + FS + "java");
        java.mkdirs();
        File resources = new File(outputDirectory, "src" + FS + "main" + FS + "resources");
        resources.mkdirs();

        File base = java;

        ArtifactRepositoryLayout layout = new DefaultRepositoryLayout();
        List<ArtifactRepository> repositories = remote();
        Artifact artifact = artifactFactory.createArtifactWithClassifier(groupId, artifactId, version, "jar", "sources");
        String path = layout.pathOf(artifact);
        artifact.setFile(new File(localRepository.getBasedir(), path));
        Artifact pomArtifact = artifactFactory.createBuildArtifact(groupId, artifactId, version, "pom");
        path = layout.pathOf(pomArtifact);
        pomArtifact.setFile(new File(localRepository.getBasedir(), path));
        try {
            wagonManager.getArtifact(artifact, repositories);
            wagonManager.getArtifact(pomArtifact, repositories);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(pomArtifact.getFile());
            outputStream = new FileOutputStream(new File(outputDirectory, "pom.xml"));
            IOUtil.copy(inputStream, outputStream);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            IOUtil.close(inputStream);
            IOUtil.close(outputStream);
        }

        try {
            ZipFile zipFile = new ZipFile(artifact.getFile());
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                File dest = new File(base, entry.getName());
                if (entry.isDirectory()) {
                    dest.mkdirs();
                } else {
                    try {
                        inputStream = zipFile.getInputStream(entry);
                        outputStream = new FileOutputStream(dest);
                        IOUtil.copy(inputStream, outputStream);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage(), e);
                    } finally {
                        IOUtil.close(inputStream);
                        IOUtil.close(outputStream);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(java);
        scanner.setExcludes(new String[]{"**/*.java"});
        scanner.scan();
        String[] files = scanner.getIncludedFiles();
        move(java, resources, files);
    }

    void move(File src, File dest, String[] files) {
        if (files == null) {
            return;
        }

        InputStream is = null;
        OutputStream os = null;
        for (String file : files) {
            File in = new File(src, file);
            if (in.isDirectory()) {
                continue;
            }
            File out = new File(dest, file);
            File parent = out.getParentFile();
            if (!parent.isDirectory()) {
                parent.mkdirs();
            }
            try {
                try {
                    is = new FileInputStream(in);
                    os = new FileOutputStream(out);
                    IOUtil.copy(is, os);
                    in.delete();
                } finally {
                    IOUtil.close(is);
                    IOUtil.close(os);
                }

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    List<ArtifactRepository> remote() {
        List<ArtifactRepository> result = new ArrayList<ArtifactRepository>();
        List<Profile> profiles = settings.getProfiles();
        if (profiles != null) {
            for (int i = 0; i < profiles.size(); i++) {
                Profile profile = profiles.get(i);
                List<Repository> repositories = profile.getRepositories();
                if (repositories != null) {
                    for (int j = 0; j < repositories.size(); j++) {
                        Repository repository = repositories.get(j);
                        ArtifactRepository artifactRepository =
                            artifactRepositoryFactory.createArtifactRepository(
                                repository.getId(),
                                repository.getUrl(),
                                new DefaultRepositoryLayout(),
                                new ArtifactRepositoryPolicy(),
                                new ArtifactRepositoryPolicy());
                        result.add(artifactRepository);
                    }
                }
            }
        }
        return result;
    }

    public static MavenProject getProject(
        File pom,
        ProjectBuilderConfiguration config,
        MavenProjectBuilder builder,
        Log log) throws ProjectBuildingException {

        if (pom.exists()) {
            if (pom.length() == 0) {
                throw new ProjectBuildingException(
                    "unknown",
                    String.format(
                        "The file %s you specified has zero length.",
                        pom.getAbsolutePath()));
            }
        }

        log.debug(String.format("Build maven project from pom %s",
                                pom.getAbsolutePath()));

        return builder.build(pom, config);
    }

}
