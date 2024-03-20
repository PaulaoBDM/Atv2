package com.example.atv2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String input = "";
    private double num1 = 0;
    private double num2 = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Definir listeners para botões de números
        ButtonClickListener buttonClickListener = new ButtonClickListener();
        int[] numButtonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        for (int id : numButtonIds) {
            findViewById(id).setOnClickListener(buttonClickListener);
        }

        // Definir listeners para botões de operadores
        int[] operatorButtonIds = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};
        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(buttonClickListener);
        }

        // Listener para botão de igual
        findViewById(R.id.btnEquals).setOnClickListener(view -> calculate());

        // Listener para botão de limpar
        findViewById(R.id.btnClear).setOnClickListener(view -> clear());
    }

    // Método para adicionar texto ao display
    private void appendInput(String value) {
        input += value;
        display.setText(input);
    }

    // Método para limpar o display
    private void clear() {
        input = "";
        num1 = 0;
        num2 = 0;
        operator = "";
        display.setText("");
    }

    // Método para realizar cálculos
    private void calculate() {
        if (!input.isEmpty()) {
            String[] values = input.split(" ");
            if (values.length == 3) {
                num1 = Double.parseDouble(values[0]);
                operator = values[1];
                num2 = Double.parseDouble(values[2]);
                double result = 0;
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
                input = String.valueOf(result);
            }
        }
    }

    // Classe para lidar com cliques nos botões
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnAdd) {
                appendInput(" + ");
            } else if (view.getId() == R.id.btnSubtract) {
                appendInput(" - ");
            } else if (view.getId() == R.id.btnMultiply) {
                appendInput(" * ");
            } else if (view.getId() == R.id.btnDivide) {
                appendInput(" / ");
            } else {
                Button button = (Button) view;
                String value = button.getText().toString();
                appendInput(value);
            }
        }
    }
}
