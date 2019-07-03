package thunderbytes.com.formulanews.Dialogue;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;


import androidx.fragment.app.DialogFragment;


public class LogoutDialogue extends DialogFragment {


    OnLogoutDialogueListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener =(OnLogoutDialogueListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement OnLogoutDialogueListener");
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder vBuilder = new AlertDialog.Builder(getActivity());

        vBuilder.setTitle("Effettuare il logout?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.signOut();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return vBuilder.create();
    }
    public interface OnLogoutDialogueListener{
        void signOut();
    }
}
