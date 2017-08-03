package com.ducnd.grantpermission.ultis;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ducnd.grantpermission.R;

/**
 * Created by ducnd on 8/4/17.
 */

public class ConfirmDialog extends Dialog implements View.OnClickListener {
    private IConfirmDialog mInterf;

    public ConfirmDialog(@NonNull Context context, @StringRes int resContent, IConfirmDialog interf) {
        super(context);
        mInterf = interf;
        inits(context.getString(resContent));
    }

    public ConfirmDialog(@NonNull Context context, String content, IConfirmDialog interf) {
        super(context);
        inits(content);
        mInterf = interf;
    }

    private void inits(String content) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_confirm);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_ok).setOnClickListener(this);

        ((TextView) findViewById(R.id.tv_content)).setText(content);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                dismiss();
                mInterf.onClickOk();
                break;
            case R.id.btn_cancel:
                dismiss();
                mInterf.onClickCancel();
                break;
            default:
                break;
        }
    }


    public interface IConfirmDialog {
        void onClickCancel();

        void onClickOk();
    }
}
