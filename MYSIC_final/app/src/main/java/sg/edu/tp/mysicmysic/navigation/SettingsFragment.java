package sg.edu.tp.mysicmysic.navigation;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import sg.edu.tp.mysicmysic.R;


public class SettingsFragment extends Fragment {

    boolean customNightMode;
    Dialog myDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        //initialize showPopup onclick
        ImageButton btnShowPopup = (ImageButton) view.findViewById(R.id.btnShowPopup);
        btnShowPopup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showPopup(view);
            }

        });

        myDialog = new Dialog(getContext());
        return view;

    }


    public void showPopup(View view) {

        TextView txtclose;

        myDialog.setContentView(R.layout.popup_about_app);
        txtclose =(TextView) myDialog.findViewById(R.id.txtClose);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    //close app and stop all running processes - exit app
    public void exitApp(View view) {
        getActivity().finish();
        System.exit(0);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View main_bg          = getActivity().findViewById(R.id.main_bg);

        TextView title        = getView().findViewById(R.id.title);

        View action_bar       = getActivity().findViewById(R.id.action_bar);
        View action_bar_extra = getActivity().findViewById(R.id.action_bar_extra);

        View switchDarkMode   = getActivity().findViewById(R.id.switchDarkMode);
        View switchNightMode  = view.findViewById(R.id.switchNightMode);


        switchNightMode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (customNightMode) {

                    main_bg.setBackgroundResource(R.drawable.wallpaper);
                    action_bar.setBackgroundColor(Color.argb(255,143,70,238));
                    title.setTextColor(Color.WHITE);;

                    action_bar_extra.setVisibility(View.VISIBLE);
                    switchDarkMode.setVisibility(View.VISIBLE);

        }      else {

                    main_bg.setBackgroundResource(R.drawable.night_mode);
                    action_bar.setBackgroundColor(Color.argb(255,166,207,206));
                    title.setTextColor(Color.BLACK);

                    action_bar_extra.setVisibility(View.INVISIBLE);
                    switchDarkMode.setVisibility(View.INVISIBLE);

                }

                customNightMode = !customNightMode;
            }

    });
}
}

