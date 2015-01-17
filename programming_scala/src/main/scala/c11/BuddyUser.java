package c11;

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
public class BuddyUser {

    public static void main(String[] args) throws Exception {
        new Buddy().greet();
        Buddy$.MODULE$.greet();
    }

}
