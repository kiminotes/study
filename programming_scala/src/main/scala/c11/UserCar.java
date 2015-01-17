package c11;

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 * @date 2015-01-17
 */
public class UserCar {

    public static void main(String[] args) throws Exception {
        Car car = new Car(2009);
        System.out.println(car);
        car.drive(10);
        System.out.println(car);
    }
}
