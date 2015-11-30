package com.nowocode.studymobile;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Martin on 18.11.2015.
 */
public class DeleteQuestionDialog extends DialogFragment {
    DialogListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (DialogListener) activity;
        } catch (Exception e){
            System.out.println("Error with on attach");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Remove Question")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       listener.onDialogPositiveClick(DeleteQuestionDialog.this);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(DeleteQuestionDialog.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public interface DialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
}


