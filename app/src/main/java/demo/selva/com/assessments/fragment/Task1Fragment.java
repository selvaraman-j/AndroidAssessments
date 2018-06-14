package demo.selva.com.assessments.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import demo.selva.com.assessments.R;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/13/2018
 */

public class Task1Fragment extends Fragment {
    private EditText edtInput;
    private TextView outputText;
    private int input[]/* = {3, 4, -1, 1}*/;
    private int inputSize = 0;

    public static Task1Fragment getInstance() {
        return new Task1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task1, container, false);
        initialiseViews(view);
        return view;
    }

    /**
     * This method is used to initialise the views
     *
     * @param view the View
     */
    private void initialiseViews(View view) {
        edtInput = view.findViewById(R.id.edit_task1_input);
        Button findMissedLowestNo = view.findViewById(R.id.find_missed_no);
        outputText = view.findViewById(R.id.text_lowest_no);
        findMissedLowestNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (-1 != parseInput())
                    findMissedLowestNo();
                else {
                    outputText.setText(getString(R.string.text_not_valid_input));
                }
            }
        });
    }

    /**
     * This  method is used to parse the user input into the array
     *
     * @return int return the integer if valid input otherwise -1
     */
    private long parseInput() {
        String inputText = edtInput.getText().toString();
        String split[] = inputText.split(",");
        int size = split.length;
        input = new int[split.length];
        for (int i = 0; i < size; i++) {
            try {
                input[i] = Integer.parseInt(split[i]);
            } catch (NumberFormatException n) {
                return input[i] = -1;
            }
        }
        inputSize = input.length;
        return inputSize;
    }

    /**
     * This method is used to find missed smallest no
     * from the positive input sequence
     */
    private void findMissedLowestNo() {
        int[] positiveInput = findPositiveNo();
        int positiveInputSize = positiveInput.length;
        // Mark positiveInput[i] as visited by making
        // arr[positiveInput[i] - 1] negative. Note that
        // 1 is subtracted because index start
        for (int i = 0; i < positiveInputSize; i++) {
            if (Math.abs(positiveInput[i]) - 1 < positiveInputSize
                    && positiveInput[Math.abs(positiveInput[i]) - 1] > 0) {
                positiveInput[Math.abs(positiveInput[i]) - 1] = -positiveInput[Math.abs(positiveInput[i]) - 1];
            }
        }

        for (int i = 0; i < positiveInputSize; i++) {
            if (positiveInput[i] > 0) {
                outputText.setText(String.format(getString(R.string.text_output), (i + 1)));
                return;
            }
        }
        outputText.setText(String.format(getString(R.string.text_output), (positiveInputSize + 1)));
    }

    /**
     * This method is used to return only positive inputs
     *
     * @return int[] the positive array
     */
    private int[] findPositiveNo() {
        int totalNonPositive = shiftNonPositiveNo();
        int positiveInput[] = new int[inputSize - shiftNonPositiveNo()];
        int totalPositiveNo = 0;
        for (int i = totalNonPositive; i < inputSize; i++) {
            positiveInput[totalPositiveNo] = input[i];
            totalPositiveNo++;
        }
        return positiveInput;
    }

    /**
     * This method is used to shift the non positive no to left side of the array
     *
     * @return int the count of non positive no's
     */
    private int shiftNonPositiveNo() {
        int nonPosNoCount = 0;
        for (int i = 0; i < inputSize; i++) {
            if (input[i] <= 0) {
                int temp = input[i];
                input[i] = input[nonPosNoCount];
                input[nonPosNoCount] = temp;
                nonPosNoCount++;
            }
        }
        return nonPosNoCount;
    }
}
