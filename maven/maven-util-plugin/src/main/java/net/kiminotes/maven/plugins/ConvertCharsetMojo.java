package net.kiminotes.maven.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.DirectoryScanner;
import org.codehaus.plexus.util.IOUtil;
import org.mozilla.universalchardet.UniversalDetector;

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2014-06-29
 * @goal convert-charset
 * @requiresProject false
 */
public class ConvertCharsetMojo extends AbstractMojo {

    /**
     * @parameter expression="${directory}"
     * @required
     */
    private File directory;

    /**
     * @parameter expression="${output.charset}" default-value="UTF-8"
     */
    private String outputCharset;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        try {
            directory = directory.getCanonicalFile();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(directory);
        scanner.setIncludes(new String[]{"**/*.java"});
        scanner.scan();
        String[] files = scanner.getIncludedFiles();
        if (files != null) {
            for (String file : files) {
                convert(new File(directory, file));
            }
        }
    }

    UniversalDetector detector = new UniversalDetector(null);

    void convert(File file) {
        FileInputStream is = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(file);
            byte[] data = IOUtil.toByteArray(is);
            detector.reset();
            detector.handleData(data, 0, data.length);
            detector.dataEnd();
            String charset = detector.getDetectedCharset();
            if (charset != null
                && !outputCharset.equalsIgnoreCase(charset)) {
                os = new FileOutputStream(file);
                os.write(new String(data, charset).getBytes(outputCharset));
                getLog().info("Convert " + file.getAbsolutePath() + " from " + charset + " to " + outputCharset);
            }
        } catch (Exception e) {
            getLog().error("Failed to convert " + file);
        } finally {
            IOUtil.close(is);
            IOUtil.close(os);
        }
    }

}
