package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    View button0 = findViewById(R.id.button0);
    View button1 = findViewById(R.id.button1);
    View button2 = findViewById(R.id.button2);
    View button3 = findViewById(R.id.button3);
    View button4 = findViewById(R.id.button4);
    View button5 = findViewById(R.id.button5);
    View button6 = findViewById(R.id.button6);
    View button7 = findViewById(R.id.button7);
    View button8 = findViewById(R.id.button8);
    View button9 = findViewById(R.id.button9);
    View buttonBackspace = findViewById(R.id.buttonBackSpace);
    View buttonClear = findViewById(R.id.buttonClear);
    View buttonPlus = findViewById(R.id.buttonPlus);
    View buttonMinus = findViewById(R.id.buttonMinus);
    View buttonEquals = findViewById(R.id.buttonEquals);
    TextView textViewOutput = findViewById(R.id.textViewCalculatorOutput);

    textViewOutput.setText(calculator.output());

    setClickListener(button0, 0, textViewOutput);
    setClickListener(button1, 1, textViewOutput);
    setClickListener(button2, 2, textViewOutput);
    setClickListener(button3, 3, textViewOutput);
    setClickListener(button4, 4, textViewOutput);
    setClickListener(button5, 5, textViewOutput);
    setClickListener(button6, 6, textViewOutput);
    setClickListener(button7, 7, textViewOutput);
    setClickListener(button8, 8, textViewOutput);
    setClickListener(button9, 9, textViewOutput);

    buttonBackspace.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.deleteLast();
        textViewOutput.setText(calculator.output());

      }
    });

    buttonClear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.clear();
        textViewOutput.setText(calculator.output());

      }
    });

    buttonPlus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertPlus();
        textViewOutput.setText(calculator.output());

      }
    });

    buttonMinus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertMinus();
        textViewOutput.setText(calculator.output());

      }
    });

    buttonEquals.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertEquals();
        textViewOutput.setText(calculator.output());

      }
    });

  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);

    outState.putSerializable("calculator", calculator.saveState());

  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

    Serializable state = savedInstanceState.getSerializable("calculator");
    calculator.loadState(state);
    TextView textViewOutput = findViewById(R.id.textViewCalculatorOutput);
    textViewOutput.setText(calculator.output());

  }

  private void setClickListener(View view, int digit, TextView textViewOutput)
  {
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(digit);
        textViewOutput.setText(calculator.output());

      }
    });
  }
}