package com.example.a47499.pwdManager.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a47499.pwdManager.MyApplication;
import com.example.a47499.pwdManager.R;
import com.example.a47499.pwdManager.bean.PwdModel;
import com.example.a47499.pwdManager.db.MySQLiteOpenHelper;
import com.example.a47499.pwdManager.utils.StringUtils;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.List;

/**
 * Created by 47499 on 2018/9/23.
 */

public class MyDialog {

    public static NiftyDialogBuilder getInstance(Context context, PwdModel pwdModel) {
        final View view = getView(context);
        TextView projectNameView = view.findViewById(R.id.projectName);
        TextView nameView = view.findViewById(R.id.name);
        TextView pwdView = view.findViewById(R.id.pwd);
        projectNameView.setText(pwdModel.getProjectName());
        nameView.setText(pwdModel.getName());
        pwdView.setText(pwdModel.getPwd());
        return getPwdDialog(context, view, pwdModel);
    }

    public static NiftyDialogBuilder getInstance(Context context) {
        final View view = getView(context);
        return getPwdDialog(context, view, null);
    }

    private static NiftyDialogBuilder getPwdDialog(Context context, final View view, final PwdModel pwdModel) {

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(context);
        final MyApplication app = MyApplication.getInstance();
        final MySQLiteOpenHelper dbHelper = app.getDbHelper();
        return dialogBuilder
                // 重点设置
                .withEffect(getRandomEffectstype())        //设置对话框弹出样式
                .setCustomView(view, context) // 设置自定义对话框的布局
                .withDuration(700)              //动画显现的时间（时间长就类似放慢动作）

                // 基本设置
                .withTitle("添加帐号")         //设置对话框标题
                .withTitleColor("#FFFFFF")          //设置标题字体颜色
                .withDividerColor("#11000000")      //设置分隔线的颜色

                .withMessage(null)//设置对话框显示内容
//                        .withMessageColor("#FFFFFFFF")       //设置消息字体的颜色
                .withDialogColor("#FFE74C3C")        //设置对话框背景的颜色
                //.withIcon(getResources().getDrawable(R.drawable.logo)) //设置标题的图标
                // 设置是否模态，默认false，表示模态，
                //要求必须采取行动才能继续进行剩下的操作 | isCancelable(true)
                .isCancelableOnTouchOutside(true)
                .withButton1Text("确定")               //设置按钮1的文本
                .withButton2Text("取消")          //设置按钮2的文本

                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView projectNameView = view.findViewById(R.id.projectName);
                        TextView nameView = view.findViewById(R.id.name);
                        TextView pwdView = view.findViewById(R.id.pwd);
                        String project = projectNameView.getText().toString();
                        String name = nameView.getText().toString();
                        String pwd = pwdView.getText().toString();
                        if (StringUtils.isSpace(project) || StringUtils.isSpace(name) || StringUtils.isSpace(pwd)) {
                            Toast.makeText(v.getContext(), "请输入完整信息", Toast.LENGTH_SHORT).show();
                        } else {
                            if (pwdModel != null) {
                                pwdModel.setProjectName(project);
                                pwdModel.setName(name);
                                pwdModel.setPwd(pwd);
                                dbHelper.update(pwdModel, app.getPwdTableName());
                                Toast.makeText(v.getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                                app.getAdapter().updateLV((List<PwdModel>) dbHelper.selectList(app.getPwdTableName(), ""));
                            } else {
                                PwdModel pwdModel = new PwdModel(project, name, pwd);
                                dbHelper.add(pwdModel, app.getPwdTableName());
                                Toast.makeText(v.getContext(), "添加成功", Toast.LENGTH_SHORT).show();
                                app.getAdapter().updateLV((List<PwdModel>) dbHelper.selectList(app.getPwdTableName(), ""));
                            }

                        }
                        dialogBuilder.dismiss();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                });
    }

    private static View getView(Context context) {
        return View.inflate(context, R.layout.pwdmodel_dialog, null);
    }

    private static Effectstype getRandomEffectstype() {
        String[] type = {"Fadein", "Slideleft", "Slidetop", "SlideBottom", "Slideright", "Fall", "Newspager", "Fliph", "Flipv", "RotateBottom", "RotateLeft", "Slit", "Shake", "Sidefill"};
        int i = (int) (type.length * Math.random());
        Effectstype effect = null;
        switch (i) {
            case 0:
                effect = Effectstype.Fadein;
                break;
            case 1:
                effect = Effectstype.Slideright;
                break;
            case 2:
                effect = Effectstype.Slideleft;
                break;
            case 3:
                effect = Effectstype.Slidetop;
                break;
            case 4:
                effect = Effectstype.SlideBottom;
                break;
            case 5:
                effect = Effectstype.Newspager;
                break;
            case 6:
                effect = Effectstype.Fall;
                break;
            case 7:
                effect = Effectstype.Sidefill;
                break;
            case 8:
                effect = Effectstype.Fliph;
                break;
            case 9:
                effect = Effectstype.Flipv;
                break;
            case 10:
                effect = Effectstype.RotateBottom;
                break;
            case 11:
                effect = Effectstype.RotateLeft;
                break;
            case 12:
                effect = Effectstype.Slit;
                break;
            case 13:
                effect = Effectstype.Shake;
                break;
        }
        return effect;
    }

}
