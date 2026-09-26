package com.example.loginscreen;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.loginscreen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime() );

            v.setPadding(systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom);
            return insets;
        });

//        binding.rootLayout.setOnClickListener(v->{
//            hideKeyboard();
//        });

        String fullText = getString(R.string.don_t_have_an_account_sing_up);
        SpannableString spannableString = new SpannableString(fullText);

        // Find the index of "Sign Up"
        int startIndex = fullText.indexOf("Sign Up");
        int endIndex = startIndex + "Sign Up".length();

        // Apply a color to "Sign Up" if found
        if (startIndex >= 0) {
            int primaryColor = getResources().getColor(R.color.primary, getTheme());
            spannableString.setSpan(
                    new ForegroundColorSpan(primaryColor),
                    startIndex,
                    endIndex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }

        // Assuming your TextView ID is txtSignUp (you'll need to update this if it's different)
//        binding.tvSignUp.setText(spannableString);

//        binding.btnLogin.setOnClickListener(view -> {
//            hideKeyboard();
//            String message = "";
//
//            if (binding.tilUsername.getEditText() != null) {
//                message += "Username: " + binding.tilUsername.getEditText().getText().toString();
//            }
//            if (binding.tilPass.getEditText() != null) {
//                message += "\nPassword: " + binding.tilPass.getEditText().getText().toString();
//            }
//
//            Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_INDEFINITE)
//                    .setAction("OK", v -> {
//
//                    }).show();
//        });
//
//        addTextInputListener();
    }

//    private void hideKeyboard() {
//        // Find the currently focused view, so we can grab the correct window token from it.
//        View view = this.getCurrentFocus();
//
//        // If no view currently has focus, create a new one, just so we can grab a window token from it
//        if (view == null) {
//            view = new View(this);
//        }
//
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
//
//    private void validateButtonSubmit() {
//        boolean isUsernameEmpty = binding.tilUsername.getEditText() == null || binding.tilUsername.getEditText().getText().toString().isEmpty();
//        boolean isPasswordEmpty = binding.tilPass.getEditText() == null || binding.tilPass.getEditText().getText().toString().isEmpty();
//
//        if (isUsernameEmpty || isPasswordEmpty) {
//            binding.btnLogin.setEnabled(false);
//        } else {
//            binding.btnLogin.setEnabled(true);
//        }
//    }
//
//    private void addTextInputListener() {
//        TextWatcher handleTextChange = new TextWatcher() {
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                validateButtonSubmit();
//            }
//        };
//
//        if (binding.tilUsername.getEditText() != null) {
//            binding.tilUsername.getEditText().addTextChangedListener(handleTextChange);
//        }
//
//        if (binding.tilPass.getEditText() != null) {
//            binding.tilPass.getEditText().addTextChangedListener(handleTextChange);
//        }
//    }
}

//class Test implements TextWatcher {
//
//    @Override
//    public void afterTextChanged(Editable s) {
//
//    }
//
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//    }
//}