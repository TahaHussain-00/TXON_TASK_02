package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resulTv,solutionTv;

    MaterialButton buttonC, buttonBrackClose,ButtonBrackOpen;
    MaterialButton buttonDivide,buttonMultiply,buttonAddition,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAc,buttonDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resulTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assignId(buttonC,R.id.Button_c);
        assignId(buttonC,R.id.Button_CloseBracket);
        assignId(buttonC,R.id.Button_OpenBracket);
        assignId(buttonC,R.id.Button_Divide);
        assignId(buttonC,R.id.Button_x);
        assignId(buttonC,R.id.Button_minius);
        assignId(buttonC,R.id.Button_addition);
        assignId(buttonC,R.id.Button_ac);
        assignId(buttonC,R.id.Button_dot);
        assignId(buttonC,R.id.Button_equals);
        assignId(buttonC,R.id.Button_1);
        assignId(buttonC,R.id.Button_2);
        assignId(buttonC,R.id.Button_3);
        assignId(buttonC,R.id.Button_4);
        assignId(buttonC,R.id.Button_5);
        assignId(buttonC,R.id.Button_6);
        assignId(buttonC,R.id.Button_7);
        assignId(buttonC,R.id.Button_8);
        assignId(buttonC,R.id.Button_9);
        assignId(buttonC,R.id.Button_0);


    }
    void assignId(MaterialButton btn,int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
                MaterialButton button = (MaterialButton) view;
                String buttonText = button.getText().toString();
                String DataToCalculate = solutionTv.getText().toString();

                if(buttonText.equals("AC"))
                {
                    solutionTv.setText("");
                    resulTv.setText("0");
                    return;
                }

                if(buttonText.equals("="))
                {
                    solutionTv.setText(resulTv.getText());
                    return;
                }
                if(buttonText.equals("C"))
                {
                    DataToCalculate = DataToCalculate.substring(0,DataToCalculate.length() -1);
                }
                else
                {
                    DataToCalculate = DataToCalculate + buttonText;
                }


                solutionTv.setText(DataToCalculate);

                String finalResult = getResult(DataToCalculate);

                if(!finalResult.equals("err"))
                {
                    resulTv.setText(finalResult);
                }

    }
        String getResult(String data)
        {
            try{
                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable  scriptable = context.initStandardObjects();
                String finalResult = context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
                if(finalResult.endsWith(".0"))
                {
                    finalResult = finalResult.replace(".0", " ");
                }
                return finalResult;
            }
            catch (Exception e)
            {
                return "err";
            }
        }

}