package com.example.storeapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import it.sephiroth.android.library.rangeseekbar.RangeSeekBar;

public class FilterDialog extends DialogFragment {

    public interface YesNoListener {
        void onYes();

        void onNo();
    }

    public static int seekValue(float value, float max) {
        if (value == max) return (int) max;

        float t = value / max;
        t = 1 - (float)Math.sqrt(1 - t * t);
        return Math.round(t * max);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.dialog_filter, null);

        // Filter price
        final TextView txtRangeStart = root.findViewById(R.id.txt_range_start);
        final TextView txtRangeEnd = root.findViewById(R.id.txt_range_end);
        final RangeSeekBar seekBar = root.findViewById(R.id.seek_range);
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onProgressChanged(RangeSeekBar bar, int start, int end, boolean fromUser) {
                txtRangeStart.setText("R$ " + Helper.formatter.format(seekValue(start, seekBar.getMax())));
                if (end == seekBar.getMax()) {
                    txtRangeEnd.setText("+ R$ 5.000,00");
                } else {
                    txtRangeEnd.setText("R$ " + Helper.formatter.format(seekValue(end, seekBar.getMax())));
                }
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar rangeSeekBar) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar rangeSeekBar) {

            }
        });

        // Filter Tags
        ChipGroup group = root.findViewById(R.id.chip_group);
        for (int i = 0; i < 10; i++) {
            Chip chip = (Chip) inflater.inflate(R.layout.chip_filter, null);
            chip.setText("Filtro [" + i + "]");
            group.addView(chip);
        }

        return new AlertDialog.Builder(getActivity())
                .setTitle("Filtros")
                .setView(root)
                .setPositiveButton("OK", null)
                .setNegativeButton("LIMPAR", null)
                .create();
    }
}