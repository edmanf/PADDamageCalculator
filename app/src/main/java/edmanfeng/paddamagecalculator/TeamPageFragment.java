package edmanfeng.paddamagecalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

/**
 * Created by t7500 on 4/3/2017.
 */

public class TeamPageFragment extends Fragment {

    private final int MIN_ORBS = 0;
    private final int MAX_ORBS = 42;

    private Spinner mComboTypeSpinner;
    private NumberPicker mComboOrbNumberPicker;
    private NumberPicker mComboEnhanceNumberPicker;
    private Spinner mComboShapeSpinner;


    public static TeamPageFragment newInstance() {
        return new TeamPageFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_team_page,container, false);
        /*
        mComboTypeSpinner = (Spinner)view.findViewById(R.id.combo_type_spinner);
        ArrayAdapter<CharSequence> comboTypeAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.orb_types, android.R.layout.simple_spinner_item);
        comboTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mComboTypeSpinner.setAdapter(comboTypeAdapter);
*/
        /*
        mComboOrbNumberPicker = (NumberPicker)view
                .findViewById(R.id.combo_orb_number_picker);
        mComboOrbNumberPicker.setMinValue(MIN_ORBS);
        mComboOrbNumberPicker.setMaxValue(MAX_ORBS);
        mComboOrbNumberPicker.setWrapSelectorWheel(true);
        mComboOrbNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // set the max enhanced orbs in a match to be the same
                // as total orbs in the match
                mComboEnhanceNumberPicker.setMaxValue(newVal);
            }
        });

        mComboEnhanceNumberPicker = (NumberPicker)view
                .findViewById(R.id.combo_enhance_number_picker);
        mComboEnhanceNumberPicker.setMinValue(MIN_ORBS);
        mComboEnhanceNumberPicker.setMaxValue(MAX_ORBS);
        mComboEnhanceNumberPicker.setWrapSelectorWheel(true);
        mComboEnhanceNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // placeholder

            }
        });

        mComboShapeSpinner = (Spinner)view.findViewById(R.id.combo_shape_spinner);
        ArrayAdapter<CharSequence> comboShapeAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.combo_shape_types, android.R.layout.simple_spinner_item);
        comboShapeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mComboShapeSpinner.setAdapter(comboShapeAdapter);*/
        return view;
    }
}
