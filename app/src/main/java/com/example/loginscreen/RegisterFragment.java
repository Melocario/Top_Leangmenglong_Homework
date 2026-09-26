package com.example.loginscreen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.example.loginscreen.databinding.FragmentLoginBinding;
import com.example.loginscreen.databinding.FragmentRegisterBinding;
import com.google.android.material.snackbar.Snackbar;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the Window and its Controller via the host Activity
        Window window = requireActivity().getWindow();
        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, window.getDecorView());

        if (controller != null) {
            // 1. Tell the system to hide both the Status Bar and the Navigation Bar
            controller.hide(WindowInsetsCompat.Type.systemBars());

            // 2. Choose how the bars behave when a user swipes from the edge
            // BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE: Temporarily shows the bars as semi-transparent and hides them again automatically
            controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        binding.registerRootLayout.setOnClickListener(v->{
            hideKeyboardFromFragment();
        });

        // 1. Get the text string. Double-check if the resource text says "Sign Up" or "Sing Up"
        String fullText = getString(R.string.already_have_an_account_log_in);
        SpannableString spannableString = new SpannableString(fullText);

        // 2. Define the exact target phrase you want to highlight
        String targetPhrase = "Log in"; // or "Sing Up" depending on your strings.xml text

        // Find the index of the phrase
        int startIndex = fullText.indexOf(targetPhrase);
        int endIndex = startIndex + targetPhrase.length();

        // Apply a color to the target phrase if found
        if (startIndex >= 0) {
            // ContextCompat is the modern, safest way to fetch colors in a Fragment
            int primaryColor = ContextCompat.getColor(requireContext(), R.color.primary);

            spannableString.setSpan(
                    new ForegroundColorSpan(primaryColor),
                    startIndex,
                    endIndex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        }

        // Set the styled text to your TextView using View Binding
        binding.tvLogin.setText(spannableString);

        binding.tvLogin.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).popBackStack();
        });

        binding.btnRegister.setOnClickListener(view -> {
            hideKeyboardFromFragment();
            String message = "";

            if (binding.tilEmail.getEditText() != null) {
                message += "Email: " + binding.tilEmail.getEditText().getText().toString();
            }
            if (binding.tilUsername.getEditText() != null) {
                message += " Username: " + binding.tilUsername.getEditText().getText().toString();
            }
            if (binding.tilPass.getEditText() != null) {
                message += "\nPassword: " + binding.tilPass.getEditText().getText().toString();
            }
            if (binding.tilConfirmPass.getEditText() != null) {
                message += " ConfirmPassword: " + binding.tilConfirmPass.getEditText().getText().toString();
            }

            Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", v -> {

                    }).show();
        });

        addTextInputListener();

        return binding.getRoot();
    }

    private void hideKeyboardFromFragment() {
        View view = getView();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private void validateButtonSubmit() {
        boolean isUsernameEmpty = binding.tilUsername.getEditText() == null || binding.tilUsername.getEditText().getText().toString().isEmpty();
        boolean isPasswordEmpty = binding.tilPass.getEditText() == null || binding.tilPass.getEditText().getText().toString().isEmpty();

        if (isUsernameEmpty || isPasswordEmpty) {
            binding.btnRegister.setEnabled(false);
        } else {
            binding.btnRegister.setEnabled(true);
        }
    }

    private void addTextInputListener() {
        TextWatcher handleTextChange = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateButtonSubmit();
            }
        };

        if (binding.tilUsername.getEditText() != null) {
            binding.tilUsername.getEditText().addTextChangedListener(handleTextChange);
        }

        if (binding.tilPass.getEditText() != null) {
            binding.tilPass.getEditText().addTextChangedListener(handleTextChange);
        }
    }
}