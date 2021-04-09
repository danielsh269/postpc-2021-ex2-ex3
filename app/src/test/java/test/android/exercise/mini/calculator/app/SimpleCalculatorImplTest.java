package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-"; // decide the expected output when having a single minus
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }

  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    assertEquals("9+", calculatorUnderTest.output());
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }
  @Test
  public void when_savingState_should_loadThatStateCorrectly2(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);


    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+79+30-300+300", calculatorUnderTest.output());
  }
  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    // implement the test based on this method's name.
    //  you can get inspiration from the test method `when_savingState_should_loadThatStateCorrectly()`
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    SimpleCalculatorImpl calculatorUnderTest2 = new SimpleCalculatorImpl();

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest2.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }
  @Test
  public void when_thereIsNoEqualYet_then_outputShouldShowAllHistory()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    assertEquals("21+81-2", calculatorUnderTest.output());
  }
  @Test
  public void when_thereIsAnEqual_then_outputShouldOnlyBeTheValue()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    assertEquals("100", calculatorUnderTest.output());
  }
  @Test
  public void when_inputHasMultipleOrders_then_onlyTheFirstOrderIsSeen()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    assertEquals("0+",calculatorUnderTest.output());

    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    assertEquals("0+",calculatorUnderTest.output());

    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    assertEquals("0-",calculatorUnderTest.output());


    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    assertEquals("0-",calculatorUnderTest.output());

    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    assertEquals("15-7",calculatorUnderTest.output());
  }
  @Test
  public void when_callingDeleteLastAndThereIsNoInput_then_theOutputShouldBeZero()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());

    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
  }
  @Test
  public void when_callingDeleteLastAndInsertAfterIt_then_outputShouldBeCorrect()
  {

    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(5);
    assertEquals("5+7-125", calculatorUnderTest.output());
  }
  @Test
  public void when_callingClearMultipleTimes_then_outputShouldBeCorrect()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    assertEquals("1", calculatorUnderTest.output());
  }
  @Test
  public void when_callingInsertEqualMultipleTimes_then_outputShouldBeCorrect()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    assertEquals("4", calculatorUnderTest.output());
  }
  @Test
  public void whenCallingMinusAfterIsEqual_then_outputShouldBeCorrect()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    assertEquals("-111-333", calculatorUnderTest.output());
  }
  @Test
  public void when_savingStateAndLoadingStateToSecondCalculatorAndInsertingMoreThings_then_outputShouldBeCorrect()
  {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    SimpleCalculatorImpl calculatorUnderTest2 = new SimpleCalculatorImpl();

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest2.loadState(savedState);
    calculatorUnderTest2.insertEquals();
    calculatorUnderTest2.insertMinus();
    calculatorUnderTest2.insertDigit(1);
    calculatorUnderTest2.insertMinus();
    calculatorUnderTest2.insertDigit(1);
    calculatorUnderTest2.insertEquals();
    calculatorUnderTest2.insertPlus();
    calculatorUnderTest2.insertDigit(3);
    calculatorUnderTest2.insertDigit(0);
    calculatorUnderTest2.deleteLast();
    calculatorUnderTest2.insertDigit(1);
    calculatorUnderTest2.insertEquals();
    assertEquals("41", calculatorUnderTest2.output());

    savedState = calculatorUnderTest2.saveState();
    calculatorUnderTest.loadState(savedState);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    assertEquals("41-1", calculatorUnderTest.output());

  }
  // TODO:
  //  the existing tests are not enough since they only test simple use-cases with small inputs.
  //  write at least 10 methods to test correct behavior with complicated inputs or use-cases.
  //  examples:
  //  - given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
  //  - given input "9<Clear>12<Clear>8-7=", expected output is "1"
  //  - given input "8-7=+4=-1=", expected output is "4"
  //  - given input "999-888-222=-333", expected output is "-111-333"
  //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
  //  etc etc.
  //  feel free to be creative in your tests!
}