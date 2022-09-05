package swen90006.mfa;

import java.util.List;
import java.util.ArrayList;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;

import org.junit.*;
import static org.junit.Assert.*;

//By extending PartitioningTests, we inherit the tests from that class
public class BoundaryTests
    extends PartitioningTests
{
    //Add another test
    @Test public void anotherTest()
    {
	//include a message for better feedback
	final int expected = 2;
	final int actual = 2;
	assertEquals("Some failure message", expected, actual);
    }

    // getData
    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void getData_B1() throws Throwable
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

    @Test()
    public void getData_B2() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", null);
        mfa.login("Tester", "qwER1234!!!");
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(1);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.getData("Tester",0);
    }

    @Test()
    public void getData_B3() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", null);
        mfa.login("Tester", "qwER1234!!!");
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(1);
        List<Integer> dataset3 = new ArrayList<>();
        dataset3.add(2);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.addData("Tester", dataset3);
        mfa.getData("Tester",1);
    }

    @Test()
    public void getData_B4() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", null);
        mfa.login("Tester", "qwER1234!!!");
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(1);
        List<Integer> dataset3 = new ArrayList<>();
        dataset3.add(2);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.addData("Tester", dataset3);
        mfa.getData("Tester",2);
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void getData_B5() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", null);
        mfa.login("Tester", "qwER1234!!!");
        List<Integer> dataset1 = new ArrayList<>();
        dataset1.add(10);
        List<Integer> dataset2 = new ArrayList<>();
        dataset2.add(1);
        List<Integer> dataset3 = new ArrayList<>();
        dataset3.add(2);
        mfa.addData("Tester", dataset1);
        mfa.addData("Tester", dataset2);
        mfa.addData("Tester", dataset3);
        mfa.getData("Tester",3);
    }

    // register

    @Test()
    public void register_B6() throws Throwable
    {
        mfa.register("Tester", "qwER1234!!!", null);
    }

    @Test()
    public void register_B7() throws Throwable
    {
        mfa.register("Test", "qwER1234!!!", null);
    }

    @Test(expected = InvalidUsernameException.class)
    public void register_B8() throws Throwable
    {
        mfa.register("Tes", "qwER1234!!!", null);
    }

    @Test(expected = InvalidUsernameException.class)
    public void register_B9() throws Throwable
    {
        mfa.register("Tester@", "qwER1234!!!", null);
    }

    @Test()
    public void register_B10() throws Throwable
    {
        mfa.register("TesterA", "qwER1234!!!", null);
    }

    @Test()
    public void register_B11() throws Throwable
    {
        mfa.register("TesterB", "qwER1234!!!", null);
    }

    @Test()
    public void register_B12() throws Throwable
    {
        mfa.register("TesterZ", "qwER1234!!!", null);
    }

    @Test(expected = InvalidUsernameException.class)
    public void register_B13() throws Throwable
    {
        mfa.register("Tester[", "qwER1234!!!", null);
    }

    @Test(expected = InvalidUsernameException.class)
    public void register_B14() throws Throwable
    {
        mfa.register("Tester`", "qwER1234!!!", null);
    }

    @Test()
    public void register_B15() throws Throwable
    {
        mfa.register("Testera", "qwER1234!!!", null);
    }

    @Test()
    public void register_B16() throws Throwable
    {
        mfa.register("Testerb", "qwER1234!!!", null);
    }

    @Test()
    public void register_B17() throws Throwable
    {
        mfa.register("Testerz", "qwER1234!!!", null);
    }

    @Test(expected = InvalidUsernameException.class)
    public void register_B18() throws Throwable
    {
        mfa.register("Tester{", "qwER1234!!!", null);
    }

    @Test(expected = InvalidPasswordException.class)
    public void register_B19() throws Throwable
    {
        mfa.register("Tester", "qwER////!!!", null);
    }

    @Test()
    public void register_B20() throws Throwable
    {
        mfa.register("Tester", "qwER0000!!!", null);
    }

    @Test()
    public void register_B21() throws Throwable
    {
        mfa.register("Tester", "qwER1111!!!", null);
    }

    @Test()
    public void register_B22() throws Throwable
    {
        mfa.register("Tester", "qwER9999!!!", null);
    }

    @Test(expected = InvalidPasswordException.class)
    public void register_B23() throws Throwable
    {
        mfa.register("Tester", "qwER::::!!!", null);
    }

    @Test(expected = InvalidPasswordException.class)
    public void register_B24() throws Throwable
    {
        mfa.register("Tester", "@@@@1234!!!", null);
    }

    @Test()
    public void register_B25() throws Throwable
    {
        mfa.register("Tester", "qwAA1234!!!", null);
    }

    @Test()
    public void register_B26() throws Throwable
    {
        mfa.register("Tester", "qwBB1234!!!", null);
    }

    @Test()
    public void register_B27() throws Throwable
    {
        mfa.register("Tester", "qwZZ1234!!!", null);
    }

    @Test(expected = InvalidPasswordException.class)
    public void register_B28() throws Throwable
    {
        mfa.register("Tester", "[[[[1234!!!", null);
    }





}
