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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t7500 on 4/3/2017.
 */

public class TeamPageFragment extends Fragment {

    private final int MIN_ORBS = 1;
    private final int MAX_ORBS = 42;

    private Spinner mComboTypeSpinner;
    private Spinner mComboOrbNumberSpinner;
    private Spinner mComboEnhanceNumberSpinner;
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

        mComboTypeSpinner = (Spinner)view.findViewById(R.id.combo_type_spinner);
        ArrayAdapter<CharSequence> comboTypeAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.orb_types, android.R.layout.simple_spinner_item);
        comboTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mComboTypeSpinner.setAdapter(comboTypeAdapter);

        List<Integer> orbCountLimits = new ArrayList<>();
        for (int i = MIN_ORBS; i < MAX_ORBS; i++) {
            orbCountLimits.add(i);
        }
        mComboOrbNumberSpinner = (Spinner)view
                .findViewById(R.id.combo_orb_number_spinner);
        ArrayAdapter<Integer> comboOrbNumAdapter = new ArrayAdapter<Integer>(
                getActivity(), android.R.layout.simple_spinner_item, orbCountLimits);
        comboOrbNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mComboOrbNumberSpinner.setAdapter(comboOrbNumAdapter);


        mComboEnhanceNumberSpinner = (Spinner) view
                .findViewById(R.id.combo_enhance_number_spinner);
        ArrayAdapter<Integer> comboEnhanceNumAdapter = new ArrayAdapter<Integer>(
                getActivity(), android.R.layout.simple_spinner_item, orbCountLimits);
        comboEnhanceNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mComboEnhanceNumberSpinner.setAdapter(comboEnhanceNumAdapter);
        /*
        mComboEnhanceNumberSpinner.setMinValue(MIN_ORBS);
        mComboEnhanceNumberSpinner.setMaxValue(MAX_ORBS);
        mComboEnhanceNumberSpinner.setWrapSelectorWheel(true);
        mComboEnhanceNumberSpinner.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // placeholder

            }
        });*/

        mComboShapeSpinner = (Spinner)view.findViewById(R.id.combo_shape_spinner);
        ArrayAdapter<CharSequence> comboShapeAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.combo_shape_types, android.R.layout.simple_spinner_item);
        comboShapeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mComboShapeSpinner.setAdapter(comboShapeAdapter);
        return view;
    }
}
