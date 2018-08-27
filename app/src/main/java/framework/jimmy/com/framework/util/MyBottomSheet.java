package framework.jimmy.com.framework.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import framework.jimmy.com.framework.R;

public class MyBottomSheet {
    public static void showBottomSheetTest(final Activity activity)
    {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(activity);
        View sheetView = LayoutInflater.from(activity).inflate(R.layout.bottom_sheet_test, null);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();

        LinearLayout edit = (LinearLayout) sheetView.findViewById(R.id.fragment_history_bottom_sheet_edit);
        LinearLayout delete = (LinearLayout) sheetView.findViewById(R.id.fragment_history_bottom_sheet_delete);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Edit code here;
                Toast.makeText(activity, "Edit", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete code here;
                Toast.makeText(activity, "Delete", Toast.LENGTH_SHORT).show();
            }
        });

        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // Do something
                Toast.makeText(activity, "Dismiss", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
