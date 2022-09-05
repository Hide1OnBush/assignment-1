package swen90006.mfa;

import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PartitioningTests
{
    //mfa is a standard instance variable in Java. It is available to all test methods
    protected MFA mfa;

    //Any method annotated with "@Before" will be executed before each test,
    //allowing the tester to set up some shared resources.
    @Before public void setUp()
	throws DuplicateUserException, InvalidUsernameException, InvalidPasswordException
    {
        //Initialise the MFA instance and create a dummy user. This will run before each test
	mfa = new MFA();
	mfa.register("UserNameA", "Password1!", "");
    }

    //Any method annotated with "@After" will be executed after each test,
    //allowing the tester to release any shared resources used in the setup.
    @After public void tearDown()
    {
    }

    //Any method annotation with "@Test" is executed as a test.
    @Test public void aTest()
    {
	//the assertEquals method used to check whether two values are
	//equal, using the equals method
	final int expected = 2;
	final int actual = 1 + 1;
	assertEquals(expected, actual);
    }

    @Test public void anotherTest()
	throws DuplicateUserException, InvalidUsernameException, InvalidPasswordException
    {
	mfa.register("UserNameB", "Password2!", "");

	//the assertTrue method is used to check whether something holds.
	assertTrue(mfa.isUser("UserNameB"));
	assertFalse(mfa.isUser("NonUser"));
    }

    //To test that an exception is correctly throw, specify the expected exception after the @Test
    @Test(expected = java.io.IOException.class)
    public void anExceptionTest()
	throws Throwable
    {
	throw new java.io.IOException();
    }

    //This test should fail.
    //To provide additional feedback when a test fails, an error message
    //can be included
    @Test public void aFailedTest()
    {
	//include a message for better feedback
	final int expected = 2;
	final int actual = 1 + 2;
	//Uncomment the following line to make the test fail
	//assertEquals("Some failure message", expected, actual);
    }


    //test: getData
    @Test(expected = NoSuchUserException.class)
    public void getData_EC_1() throws Throwable
    {
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(10);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.getData("Tester",1);
    }

    @Test(expected = UnauthenticatedUserException.class)
    public void getData_EC_2() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", null);
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(1);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.getData("Tester",1);
    }

    @Test
    public void getData_EC_3() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", null);
        mfa.login("Tester", "qwER1234!!!");
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(1);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.getData("Tester",1);

        assertEquals(dataset2, mfa.getData("Tester", 1));

    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void getData_EC_4() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", null);
        mfa.login("Tester", "qwER1234!!!");
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(1);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.getData("Tester",-1);
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void getData_EC_5() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", null);
        mfa.login("Tester", "qwER1234!!!");
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(1);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.getData("Tester",2);
    }

    @Test()
    public void getData_EC_6() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", "SWEN90006");
        mfa.login("Tester", "qwER1234!!!");
        mfa.isAuthenticated("Tester");
        mfa.respondToPushNotification("Tester", "SWEN90006");
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(1);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.getData("Tester",1);

        assertEquals(dataset2, mfa.getData("Tester", 1));
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void getData_EC_7() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", "SWEN90006");
        mfa.login("Tester", "qwER1234!!!");
        mfa.isAuthenticated("Tester");
        mfa.respondToPushNotification("Tester", "SWEN90006");
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(1);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.getData("Tester",-1);

        assertEquals(dataset2, mfa.getData("Tester", -1));
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void getData_EC_8() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", "SWEN90006");
        mfa.login("Tester", "qwER1234!!!");
        mfa.isAuthenticated("Tester");
        mfa.respondToPushNotification("Tester", "SWEN90006");
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(1);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.getData("Tester",2);

        assertEquals(dataset2, mfa.getData("Tester", 2));
    }

    // test: register
    @Test(expected = DuplicateUserException.class)
    public void register_EC_9() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", null);
        mfa.register("Tester", "qwER1234!!!", null);
    }

    @Test(expected = InvalidUsernameException.class)
    public void register_EC_10() throws Throwable
    {
        mfa.register("Tes", "qwER1234!!!", null);
    }

    @Test(expected = InvalidUsernameException.class)
    public void register_EC_11() throws Throwable
    {
        mfa.register("Tes12312", "qwER1234!!!", null);
    }

    @Test(expected = InvalidPasswordException.class)
    public void register_EC_12() throws Throwable
    {
        mfa.register("Tester", "2131231231234!!!", null);
    }

    @Test(expected = InvalidPasswordException.class)
    public void register_EC_13() throws Throwable
    {
        mfa.register("Tester", "qweRTYUUIO!!!", null);
    }

    @Test(expected = InvalidPasswordException.class)
    public void register_EC_14() throws Throwable
    {
        mfa.register("Tester", "qweR1234", null);
    }

    @Test()
    public void register_EC_15() throws Throwable
    {
        mfa.register("Tester", "qweR1234!!!", null);
        assertTrue(mfa.isUser("Tester"));
    }

    @Test()
    public void register_EC_16() throws Throwable
    {
        mfa.register("Tester", "qweR1234!!!", "SWEN90006");
        assertTrue(mfa.isUser("Tester"));
    }

    // test login

    @Test(expected = NoSuchUserException.class)
    public void login_EC_17() throws Throwable
    {
        mfa.login("Tester", "qweR1234!!!");
    }

    @Test(expected = IncorrectPasswordException.class)
    public void login_EC_18() throws Throwable
    {
        mfa.register("Tester", "qweR1234!!!", "SWEN90006");
        mfa.login("Tester","qweR1234!");

    }

    @Test()
    public void login_EC_19() throws Throwable
    {
        mfa.register("Tester", "qweR1234!!!", null);
        mfa.login("Tester","qweR1234!!!");
        mfa.isAuthenticated("Tester");

        assertEquals(MFA.AuthenticationStatus.SINGLE, mfa.login("Tester","qweR1234!!!"));
    }

    @Test()
    public void login_EC_20() throws Throwable
    {
        mfa.register("Tester", "qweR1234!!!", "SWEN90006");
        mfa.login("Tester","qweR1234!!!");
        mfa.isAuthenticated("Tester");
        assertEquals(MFA.AuthenticationStatus.SINGLE, mfa.login("Tester","qweR1234!!!"));
    }

    // test respondToPushNotification

    @Test(expected = NoSuchUserException.class)
    public void respondToPushNotification_EC_21() throws Throwable
    {
        mfa.respondToPushNotification("Tester", "SWEN90006");
    }

    @Test()
    public void respondToPushNotification_EC_22() throws Throwable
    {
        mfa.register("Tester", "qweR1234!!!", null);
        assertEquals(MFA.AuthenticationStatus.NONE, mfa.respondToPushNotification("Tester","SWEN90006"));
    }

    @Test(expected = IncorrectDeviceIDException.class)
    public void respondToPushNotification_EC_23() throws Throwable
    {
        mfa.register("Tester", "qweR1234!!!", "SWEN90006");
        mfa.login("Tester", "qweR1234!!!");
        mfa.respondToPushNotification("Tester", "SWEN90007");
    }

    @Test()
    public void respondToPushNotification_EC_24() throws Throwable
    {
        mfa.register("Tester", "qweR1234!!!", "SWEN90006");
        mfa.login("Tester", "qweR1234!!!");
        mfa.respondToPushNotification("Tester", "SWEN90006");
        assertEquals(MFA.AuthenticationStatus.DOUBLE, mfa.respondToPushNotification("Tester","SWEN90006"));
    }
}
