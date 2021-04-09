package android.exercise.mini.calculator.app;

import java.io.Serializable;

public class SimpleCalculatorImpl implements SimpleCalculator {


  private String output = "0";

  private int parseStringExpression()
  {
    int res = 0;
    int curr = 0;
    char last_sign = '+';
    for (char c : this.output.toCharArray()) {
      if (c == '+' || c == '-') {
        if (last_sign == '+')
          res += curr;

        else
          res -= curr;

        last_sign = c;
        curr = 0;
      } else {
        curr = (curr * 10) + Character.getNumericValue(c);
      }
    }

    if (last_sign == '+')
      res += curr;
    else
      res -= curr;

    return res;
  }

  @Override
  public String output() {
    // todo: return output based on the current state
    return this.output;
  }

  @Override
  public void insertDigit(int digit) {

    if (digit < 0 || digit > 9)
      throw  new IllegalArgumentException();

    if (this.output.compareTo("0") == 0)
      this.output = String.valueOf(digit);

    else
      this.output += String.valueOf(digit);
  }

  @Override
  public void insertPlus() {

    char lastChar = this.output.charAt(this.output.length() - 1);

    if (lastChar == '+' || lastChar == '-')
      return;

    this.output += "+";
  }

  @Override
  public void insertMinus() {

    char lastChar = this.output.charAt(this.output.length() - 1);

    if (lastChar == '+' || lastChar == '-')
      return;

    this.output += "-";
  }

  @Override
  public void insertEquals() {
    // calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
    int res = parseStringExpression();
    this.output = String.valueOf(res);
  }

  @Override
  public void deleteLast() {
    // delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here

    if (this.output.compareTo("0") == 0 || this.output.length() == 0)
    {
      this.output = "0";
    }
    else
    {
      this.output = this.output.substring(0, this.output.length() - 1);
    }

  }

  @Override
  public void clear() {
    this.output = "0";
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    state.save(this.output);
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    this.output = casted.load();
  }

  private static class CalculatorState implements Serializable {
    /*
    add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    public String output;

    /**
     * saves the current state
     * @param out the output to save
     */
    public void save(String out)
    {
      this.output = out;
    }

    /**
     * load the last state
     * @return the output of the last state
     */
    public String load()
    {
      return this.output;
    }

  }
}
