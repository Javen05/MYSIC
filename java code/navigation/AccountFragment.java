package sg.edu.tp.mysicmysic.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import sg.edu.tp.mysicmysic.Login.LoginActivity;
import sg.edu.tp.mysicmysic.R;
import sg.edu.tp.mysicmysic.SongDatabase.SongCollection;

public class AccountFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.txtSongCount)).setText("Songs: "
                + new SongCollection().getArrayLength());

        String Username;
        Bundle bundle     = this.getActivity().getIntent().getExtras();
        Username          = bundle.getString("username");

        TextView username = view.findViewById(R.id.txtUsername);
        username.setText(Username);


        //open logout interface
        MaterialButton btnToLogout   = (MaterialButton) view.findViewById(R.id.btnToLogout);
        LinearLayout logoutInterface = (LinearLayout) view.findViewById(R.id.logoutInterface);

        btnToLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                logoutInterface.setVisibility(View.VISIBLE);

            }
        });


        //logout button
        MaterialButton btnLogout = (MaterialButton) view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent logout = new Intent(getActivity(), LoginActivity.class);

                startActivity(logout);

                getActivity().finish();

            }
        });


        //cancel logout interface
        MaterialButton btnDeclineLogout = (MaterialButton) view.findViewById(R.id.btnDeclineLogout);

        btnDeclineLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                logoutInterface.setVisibility(View.INVISIBLE);

            }
        });

    }

}
