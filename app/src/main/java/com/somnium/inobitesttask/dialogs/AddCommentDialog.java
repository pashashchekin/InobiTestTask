package com.somnium.inobitesttask.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.somnium.inobitesttask.R;
import com.somnium.inobitesttask.listeners.AddCommentDialogListener;


public class AddCommentDialog {

    private AlertDialog dialog;
    private Button okAddComment;
    private EditText commentName;
    private EditText commentEmail;
    private EditText commentTxt;
    private AddCommentDialogListener listener;

    public AddCommentDialog(Context context, AddCommentDialogListener listener){
        this.listener = listener;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_add_comment, null);
        AlertDialog.Builder builderAdd = new AlertDialog.Builder(context);
        builderAdd.setView(view);
        builderAdd.setCancelable(true);
        okAddComment = view.findViewById(R.id.btn_add_comment);
        commentName = view.findViewById(R.id.dialog_comment_name);
        commentEmail = view.findViewById(R.id.dialog_comment_mail);
        commentTxt = view.findViewById(R.id.dialog_comment_text);

        okAddComment.setOnClickListener(dialog -> {
            listener.onClickAddComment(commentName.getText().toString(),commentEmail.getText().toString(),commentTxt.getText().toString());
            dismiss();
        });
        dialog = builderAdd.create();
    }


    public void show(){
        dialog.show();
    }

    public void cancel(){
        dialog.cancel();
    }

    public void dismiss(){
        dialog.dismiss();
    }
}
