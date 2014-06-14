package com.literallyelvis.assistmetic;

/**
 * Created by elvis on 3/13/14.
 */


import android.util.Log;

import java.util.Random;

public class Problem
{
    public int  operand1,
            operand2,
            opChoice,
            ansChoice,
            trueAnswer,
            max = 15;

    public int[] possibleAnswers = {0, 0, 0, 0};

    public double maxMod;

    public char[] func = { '+', '-', 'x', '÷', '%' };
    public char operate;

    public Problem()
    {
        Random number = new Random();
        operand1 = number.nextInt(max);
        Log.d("Problem.variables", "Operand1 = " + operand1);
        operand2 = number.nextInt(max);
        Log.d("Problem.variables", "Operand2 = " + operand2);

        opChoice = number.nextInt(5);

        switch (opChoice) {
            case 0:
                operate = func[0];
                trueAnswer = operand1 + operand2;
                Log.d("Problem.operandSwitch", "Addition selected. Answer is " + trueAnswer);
                break;
            case 1:
                operate = func[1];
                trueAnswer = operand1 - operand2;
                Log.d("Problem.operandSwitch", "Subtraction selected. Answer is " + trueAnswer);
                break;
            case 2:
                operate = func[2];
                trueAnswer = operand1 * operand2;
                Log.d("Problem.operandSwitch", "Multiplication selected. Answer is " + trueAnswer);
                break;
            case 3:
                operate = func[3];
                if (operand2 == 0 )
                {
                    operand2++;
                    Log.d("Problem.operandSwitch", "Division by zero crisis: averted.");
                }
                trueAnswer = operand1 / operand2;
                Log.d("Problem.operandSwitch", "Division selected. Answer is " + trueAnswer);
                break;
            case 4:
                operate = func[4];
                if( operand1 == 0 )
                {
                    operand1++;
                }

                if( operand2 == 0 )
                {
                    operand2++;
                }

                if( operand1 < operand2 )
                {
                    int temp = operand2;
                    operand2 = operand1;
                    operand1 = temp;
                    Log.d("Problem.variables", "Modulo operand swap conducted");
                }
                trueAnswer = operand1 % operand2;
                Log.d("Problem.opChoice", "Modulo selected. Answer is " + trueAnswer);
                break;
            default:
                //this should literally never happen.
                Log.wtf("ProblemGeneration", "Operand generation somehow exceeded bounds");
                operate = '!';
                break;
        }

        //applies limiting operations to ensure the possibleAnswers generated don't exceed the
        //bounds of what a normal person might suspect the answer to be.
        maxMod = trueAnswer + (trueAnswer * .5);
        if (trueAnswer <= 20) {
            max = 20;
        } else {
            maxMod = trueAnswer + (trueAnswer * .5);
            max = (int) Math.round(maxMod);
            Log.d("Problem.variables", "Max: " + max);
        }

        for (int i = 0; i < 4; i++)
        {
            if (i == 0) {
                possibleAnswers[i] = number.nextInt(max);
                Log.d("Problem.arrayPop", "possibleAnswers[0] = " + possibleAnswers[i]);
                if (possibleAnswers[i] == trueAnswer) {
                    possibleAnswers[i]++;
                    Log.d("Problem.variables", "possibleAnswers[0] has been incremented. Value: "
                            + possibleAnswers[i]);
                }
            } else {
                int tempAnswer = number.nextInt(max);
                if( tempAnswer != trueAnswer) {
                    possibleAnswers[i] = tempAnswer;
                }
                Log.d("Problem.arrayPop", "possibleAnswers[" + i + "] = " + possibleAnswers[i]);
            }
            if (trueAnswer < 0) {
                Log.d("Population.arrayPop", "trueAnswer is negative, other elements negated");
                possibleAnswers[i] *= -1;
            }
        }

        ansChoice = number.nextInt(4);
        boolean containsTrueAnswer = false;

        for( int i = 0; i < 4; i++ ){
            if( possibleAnswers[i] == trueAnswer ){
                containsTrueAnswer = true;
            }
        }

        if (containsTrueAnswer){}
        else
        {
            switch( ansChoice )
            {
                case 0:
                    possibleAnswers[0] = trueAnswer;
                    Log.d("Problem.ansChoice", "button1 chosen.");
                    break;
                case 1:
                    possibleAnswers[1] = trueAnswer;
                    Log.d("Problem.ansChoice", "button2 chosen.");
                    break;
                case 2:
                    possibleAnswers[2] = trueAnswer;
                    Log.d("Problem.ansChoice", "button3 chosen.");
                    break;
                case 3:
                    possibleAnswers[3] = trueAnswer;
                    Log.d("Problem.ansChoice", "button4 chosen.");
                    break;
                default:
                    Log.wtf("Problem.ansChoice", "somehow ansChoice generated exceeds bounds.");
                    break;
            }
        }
    }
}
