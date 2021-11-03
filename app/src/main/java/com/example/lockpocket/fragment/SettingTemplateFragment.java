package com.example.lockpocket.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.lockpocket.EditActivity;
import com.example.lockpocket.R;
import com.example.lockpocket.SigninActivity;
import com.example.lockpocket.SignupActivity;
import com.example.lockpocket.TemplateActivity;
import com.example.lockpocket.UploadRequest;
import com.example.lockpocket.account.RegisterRequest;
import com.example.lockpocket.utils.BitmapConverter;
import com.example.lockpocket.utils.LockScreen;
import com.example.lockpocket.utils.PreferenceManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class SettingTemplateFragment extends PreferenceFragmentCompat {

    Preference editButton;
    Preference editUpload;
    SwitchPreference lockToggle;
    ListPreference editTemplate;
    Preference backgroundGallery;
    Context context;
    private final int RESULT_OK = -1;
    private final int PICK_IMAGE = 1111;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_template, null);

        editButton = findPreference("key_edit");
        editUpload = findPreference("key_edit_upload");
        editTemplate = findPreference("key_edit_template");
        lockToggle = findPreference("key_lock_toggle");
        backgroundGallery = findPreference("key_background_get");
        context = getContext();
        LockScreen.getInstance().init(getContext(),true);

        if (LockScreen.getInstance().isActive()) {
            lockToggle.setChecked(true);
        } else {
            lockToggle.setChecked(false);
        }

        editButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(getContext(), EditActivity.class);
                startActivity(intent);
                getActivity().finish();
                return true;
            }
        });

        editUpload.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String upload = PreferenceManager.getString(getContext(), "edit_lockscreen");
                if(!upload.equals("")) {
                    // Network Work.
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                //성공
                                if(success)
                                {
                                }
                                //실패
                                else
                                {
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    // 서버로 볼리를 이용해서 요청한다.
                    UploadRequest uploadRequest = null;
                    try {
                        uploadRequest = new UploadRequest(upload, upload, responseListener, context);
                        RequestQueue queue = Volley.newRequestQueue(context);
                        queue.add(uploadRequest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                return true;
            }
        });

        editTemplate.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(editTemplate.getValue().equals(newValue)) return false;
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("설정을 바꾸면 편집한 화면이 초기화됩니다.")
                            .setTitle("주의")
                            .setPositiveButton("계속", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editTemplate.setValue((String) newValue);
                                    PreferenceManager.setString(getContext(), "edit_lockscreen", "");
                                    Intent intent = new Intent(getContext(), TemplateActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // nothing to do
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    return false;
                }
            }
        });

        lockToggle.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (lockToggle.isChecked()) {
                    LockScreen.getInstance().deactivate();
                    lockToggle.setChecked(false);
                } else {
                    LockScreen.getInstance().active();
                    lockToggle.setChecked(true);
                }
                return false;
            }
        });

        backgroundGallery.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PICK_IMAGE);
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                String imageUriPath = BitmapConverter.BitmapToString(selectedImage);
                PreferenceManager.setString(getContext(), "edit_background", imageUriPath);
                Toast.makeText(getContext(), "배경화면을 성공적으로 설정했습니다", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), TemplateActivity.class);
                startActivity(intent);
                getActivity().finish();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
}
