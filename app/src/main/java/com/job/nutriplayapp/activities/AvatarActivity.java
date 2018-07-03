package com.job.nutriplayapp.activities;

import android.animation.Animator;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.job.nutriplayapp.R;
import com.job.nutriplayapp.utilidades.SesionPreference;
import com.squareup.picasso.Picasso;

public class AvatarActivity extends AppCompatActivity {

    private static final String TAG = AvatarActivity.class.getSimpleName();

    private ConstraintLayout layoutMain, layoutButtons, layoutContent, lay2;
    private boolean isOpen = false;

    private ImageView a1, a2, a3, a4, a5, a6, a7, a8, a9;
    private CardView c1, c2, c3, c4, c5, c6, c7, c8, c9;

    private Button acept, cancel;

    private ImageView avatarView;

    private SesionPreference sesionPreference;

    private String uid;
    private String url1 = "https://firebasestorage.googleapis.com/v0/b/nutriplayapp.appspot.com/o/avatares%2Fav1.png?alt=media&token=fc65d708-6ea7-4b1a-85b1-08776c896775";
    private String url2 = "https://firebasestorage.googleapis.com/v0/b/nutriplayapp.appspot.com/o/avatares%2Fav2.png?alt=media&token=0d611b31-45e4-4e98-b194-143bf5efc166";
    private String url3 = "https://firebasestorage.googleapis.com/v0/b/nutriplayapp.appspot.com/o/avatares%2Fav3.png?alt=media&token=af870dd1-0e4a-450a-9aaa-2ef7243f75b4";
    private String url4 = "https://firebasestorage.googleapis.com/v0/b/nutriplayapp.appspot.com/o/avatares%2Fav4.png?alt=media&token=84b503a3-3168-4ca2-aadd-3bd18c04e66a";
    private String url5 = "https://firebasestorage.googleapis.com/v0/b/nutriplayapp.appspot.com/o/avatares%2Fav5.png?alt=media&token=030f5bb6-7061-49c6-8a3a-c4d71d8d69f9";
    private String url6 = "https://firebasestorage.googleapis.com/v0/b/nutriplayapp.appspot.com/o/avatares%2Fav6.png?alt=media&token=4d91a296-7749-47df-84eb-6a286ea5ff32";
    private String url7 = "https://firebasestorage.googleapis.com/v0/b/nutriplayapp.appspot.com/o/avatares%2Fav7.png?alt=media&token=160d662a-5495-4bb2-b050-05400405205a";
    private String url8 = "https://firebasestorage.googleapis.com/v0/b/nutriplayapp.appspot.com/o/avatares%2Fav8.png?alt=media&token=810c5977-7cf9-4d33-a2a4-997c072ac04c";
    private String url9 = "https://firebasestorage.googleapis.com/v0/b/nutriplayapp.appspot.com/o/avatares%2Fav9.png?alt=media&token=f9a9f08b-ae94-4e53-9f2b-5058ebbc7e7e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        /*sesionPreference = new SesionPreference(this);
        if(!sesionPreference.firstTime()){
            sesionPreference.setFirstTime(false);
            startActivity(new Intent(AvatarActivity.this, HomeActivity.class));
            finish();
        }*/

        layoutMain = (ConstraintLayout) findViewById(R.id.layoutMain);
        layoutButtons = (ConstraintLayout) findViewById(R.id.layoutButtons);
        layoutContent = (ConstraintLayout) findViewById(R.id.layoutContent);
        lay2 = (ConstraintLayout) findViewById(R.id.layout2);
        acept = (Button) findViewById(R.id.aceptButton);
        cancel = (Button) findViewById(R.id.cancelButton);

        a1 = (ImageView) findViewById(R.id.av1);
        a2 = (ImageView) findViewById(R.id.av2);
        a3 = (ImageView) findViewById(R.id.av3);
        a4 = (ImageView) findViewById(R.id.av4);
        a5 = (ImageView) findViewById(R.id.av5);
        a6 = (ImageView) findViewById(R.id.av6);
        a7 = (ImageView) findViewById(R.id.av7);
        a8 = (ImageView) findViewById(R.id.av8);
        a9 = (ImageView) findViewById(R.id.av9);

        c1 = (CardView) findViewById(R.id.cv1);
        c2 = (CardView) findViewById(R.id.cv2);
        c3 = (CardView) findViewById(R.id.cv3);
        c4 = (CardView) findViewById(R.id.cv4);
        c5 = (CardView) findViewById(R.id.cv5);
        c6 = (CardView) findViewById(R.id.cv6);
        c7 = (CardView) findViewById(R.id.cv7);
        c8 = (CardView) findViewById(R.id.cv8);
        c9 = (CardView) findViewById(R.id.cv9);

        Picasso.get().load(url1).priority(Picasso.Priority.HIGH).into(a1);
        Picasso.get().load(url2).priority(Picasso.Priority.HIGH).into(a2);
        Picasso.get().load(url3).priority(Picasso.Priority.HIGH).into(a3);
        Picasso.get().load(url4).priority(Picasso.Priority.NORMAL).into(a4);
        Picasso.get().load(url5).priority(Picasso.Priority.NORMAL).into(a5);
        Picasso.get().load(url6).priority(Picasso.Priority.NORMAL).into(a6);
        Picasso.get().load(url7).priority(Picasso.Priority.LOW).into(a7);
        Picasso.get().load(url8).priority(Picasso.Priority.LOW).into(a8);
        Picasso.get().load(url9).priority(Picasso.Priority.LOW).into(a9);

        avatarView = (ImageView) findViewById(R.id.avView);

    }

    public void updateAvatar1(View view) {
        float centreX = view.getX() + view.getWidth() / 2;
        float centreY = view.getY() + view.getHeight() / 2;
        update(url1);
        getXY((int) centreX, (int) centreY);
        viewAvatar((int) centreX, (int) centreY, url1);
    }

    public void updateAvatar2(View view) {
        float centreX = view.getX() + view.getWidth() / 2;
        float centreY = view.getY() + view.getHeight() / 2;
        update(url2);
        getXY((int) centreX, (int) centreY);
        viewAvatar((int) centreX, (int) centreY, url2);
    }

    public void updateAvatar3(View view) {
        float centreX = view.getX() + view.getWidth() / 2;
        float centreY = view.getY() + view.getHeight() / 2;
        update(url3);
        getXY((int) centreX, (int) centreY);
        viewAvatar((int) centreX, (int) centreY, url3);
    }

    public void updateAvatar4(View view) {
        float centreX = view.getX() + view.getWidth() / 2;
        float centreY = view.getY() + view.getHeight() / 2;
        update(url4);
        getXY((int) centreX, (int) centreY);
        viewAvatar((int) centreX, (int) centreY, url4);
    }

    public void updateAvatar5(View view) {
        float centreX = view.getX() + view.getWidth() / 2;
        float centreY = view.getY() + view.getHeight() / 2;
        update(url5);
        getXY((int) centreX, (int) centreY);
        viewAvatar((int) centreX, (int) centreY, url5);
    }

    public void updateAvatar6(View view) {
        float centreX = view.getX() + view.getWidth() / 2;
        float centreY = view.getY() + view.getHeight() / 2;
        update(url6);
        getXY((int) centreX, (int) centreY);
        viewAvatar((int) centreX, (int) centreY, url6);
    }

    public void updateAvatar7(View view) {
        float centreX = view.getX() + view.getWidth() / 2;
        float centreY = view.getY() + view.getHeight() / 2;
        update(url7);
        getXY((int) centreX, (int) centreY);
        viewAvatar((int) centreX, (int) centreY, url7);
    }

    public void updateAvatar8(View view) {
        float centreX = view.getX() + view.getWidth() / 2;
        float centreY = view.getY() + view.getHeight() / 2;
        update(url8);
        getXY((int) centreX, (int) centreY);
        viewAvatar((int) centreX, (int) centreY, url8);
    }

    public void updateAvatar9(View view) {
        float centreX = view.getX() + view.getWidth() / 2;
        float centreY = view.getY() + view.getHeight() / 2;
        update(url9);
        getXY((int) centreX, (int) centreY);
        viewAvatar((int) centreX, (int) centreY, url9);
    }

    public void getXY(final Integer x, final Integer y) {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAvatar(x, y, url1);
            }
        });
    }

    private void update(String url) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference usuarioData = FirebaseDatabase.getInstance().getReference("usuario");
        uid = user.getUid();
        usuarioData.child(uid).child("avatar").setValue(url);
    }

    private void viewAvatar(Integer x, Integer y, String url) {
        if (!isOpen) {
            int startRadius = 0;
            int endRadius = (int) Math.hypot(layoutMain.getWidth(), layoutMain.getHeight());
            Animator animator = ViewAnimationUtils.createCircularReveal(layoutButtons, x, y, startRadius, endRadius);
            layoutButtons.setVisibility(View.VISIBLE);
            lay2.setVisibility(View.GONE);
            animator.start();
            Picasso.get().load(url).into(avatarView);
            isOpen = true;
        } else {
            int startRadius = Math.max(layoutContent.getWidth(), layoutContent.getHeight());
            int endRadius = 0;
            Animator animator = ViewAnimationUtils.createCircularReveal(layoutButtons, x, y, startRadius, endRadius);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    layoutButtons.setVisibility(View.GONE);
                    lay2.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
            animator.start();
            isOpen = false;
        }
    }

    public void goHome(View view) {
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
        finish();
    }

}