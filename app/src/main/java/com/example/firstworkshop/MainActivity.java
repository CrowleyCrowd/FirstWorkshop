package com.example.firstworkshop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double firstNumber = 0;
    double result = 0;
    double aux = 0;
    String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_0 = findViewById(R.id.button0);
        Button btn_1 = findViewById(R.id.button1);
        Button btn_2 = findViewById(R.id.button2);
        Button btn_3 = findViewById(R.id.button3);
        Button btn_4 = findViewById(R.id.button4);
        Button btn_5 = findViewById(R.id.button5);
        Button btn_6 = findViewById(R.id.button6);
        Button btn_7 = findViewById(R.id.button7);
        Button btn_8 = findViewById(R.id.button8);
        Button btn_9 = findViewById(R.id.button9);

        Button btn_equal = findViewById(R.id.buttonEqual);
        Button btn_plus = findViewById(R.id.buttonPlus);
        Button btn_minus = findViewById(R.id.buttonMinus);
        Button btn_multiply = findViewById(R.id.buttonMultiply);
        Button btn_divide = findViewById(R.id.buttonDivide);

        Button btn_decimal = findViewById(R.id.buttonDecimal);

        Button btn_clear = findViewById(R.id.buttonClear);
        Button btn_delete = findViewById(R.id.buttonDelete);

        TextView txt_screen = findViewById(R.id.calculatorDisplay);

        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(btn_0);
        buttons.add(btn_1);
        buttons.add(btn_2);
        buttons.add(btn_3);
        buttons.add(btn_4);
        buttons.add(btn_5);
        buttons.add(btn_6);
        buttons.add(btn_7);
        buttons.add(btn_8);
        buttons.add(btn_9);

        for (Button button : buttons) {
            button.setOnClickListener(view -> {
                String currentText = txt_screen.getText().toString();
                String buttonText = button.getText().toString();
                if (currentText.equals("0")) {
                    txt_screen.setText(buttonText);
                } else if (result != 0) {
                    result = 0;
                    aux = 0;
                    txt_screen.setText(buttonText);
                } else {
                    txt_screen.setText(String.format("%s%s", currentText, buttonText));
                }
            });
        }

        ArrayList<Button> operators = new ArrayList<>();
        operators.add(btn_plus);
        operators.add(btn_minus);
        operators.add(btn_multiply);
        operators.add(btn_divide);

        for (Button operator : operators) {
            operator.setOnClickListener(view -> {
                firstNumber = Double.parseDouble(txt_screen.getText().toString());
                operation = operator.getText().toString();
                txt_screen.setText("0");
            });
        }

        btn_decimal.setOnClickListener(view -> {
            if (!txt_screen.getText().toString().contains(".")) {
                txt_screen.setText(String.format("%s.", txt_screen.getText().toString()));
            }
        });

        btn_delete.setOnClickListener(view -> {
            String currentValue = txt_screen.getText().toString();
            if (currentValue.length() > 1) {
                txt_screen.setText(currentValue.substring(0, currentValue.length() - 1));
            } else if (currentValue.length() == 1 && !currentValue.equals("0")) {
                txt_screen.setText("0");
            }
        });

        btn_clear.setOnClickListener(view -> {
            txt_screen.setText("0");
            firstNumber = 0;
        });
        btn_equal.setOnClickListener(view -> {
            double secondNumber = Double.parseDouble(txt_screen.getText().toString());
            txt_screen.setText("");
            txt_screen.setText(String.valueOf(secondNumber));
            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    aux = secondNumber;
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    aux = secondNumber;
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    aux = secondNumber;
                    result = firstNumber / secondNumber;
                    break;
            }
            txt_screen.setText(String.valueOf(result));
            firstNumber = result;
        });

    }
}