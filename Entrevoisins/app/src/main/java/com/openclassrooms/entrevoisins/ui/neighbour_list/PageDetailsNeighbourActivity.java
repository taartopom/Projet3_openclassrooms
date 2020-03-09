package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author sandra
 * @version 1
 */
public class PageDetailsNeighbourActivity extends AppCompatActivity {

    //Dans la première partie de la page
    @BindView(R.id.activity_page_neighbour_img_avatar)
    ImageView avatar;
    @BindView(R.id.activity_page_neighbour_tv_name)
    TextView name;
    @BindView(R.id.activity_page_neighbour_arrow_return_list)
    ImageView arrowReturn;

    //CardView Info
    @BindView(R.id.container_info_name_tv)
    TextView infoName;
    @BindView(R.id.container_info_address_tv)
    TextView infoAddress;
    @BindView(R.id.container_info_phone_tv)
    TextView infoPhone;
    @BindView(R.id.container_info_links_tv)
    TextView infoLinks;

    //CardView presentation
    @BindView(R.id.container_description_short_tv)
    TextView descriptionShort;
    @BindView(R.id.container_about_me)
    TextView aboutMe;

    //Mon favoris (ou pas)
    @BindView(R.id.star_favori)
    ImageView myFavoris;


    public Neighbour neighbour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_neighbour);
        ButterKnife.bind(this);
        Log.i("DEBUG", "activité ouverte");

        long neighbourId = getIntent().getLongExtra("neighbourId", -1L);
        neighbour = DI.getNeighbourApiService().getNeighbourById(neighbourId);

        injectInfoTexts();

        //Log.i("DEBUG", "info injecté avec succes");

        infoLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(neighbour.getLinks());
                Intent intentUri =  new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentUri);
            }
        });

        arrowReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        myFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    /**
     * @author sandra
     */
    public void injectInfoTexts() {
        name.setText(neighbour.getName());
        infoName.setText(neighbour.getName());
        infoAddress.setText(neighbour.getAddress());
        infoPhone.setText(neighbour.getPhoneNumber());
        infoLinks.setText(neighbour.getLinks());
        aboutMe.setText(neighbour.getAboutMe());
        Glide.with(avatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(avatar);
    }

}
