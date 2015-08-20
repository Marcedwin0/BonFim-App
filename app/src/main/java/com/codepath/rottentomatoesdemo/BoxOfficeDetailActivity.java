package com.codepath.rottentomatoesdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class BoxOfficeDetailActivity extends AppCompatActivity {

    private ImageView ivPosterImage;
    private TextView tvTitle;
    private TextView tvTrailer;
    private TextView tvSynopsis;
    private TextView tvCast;
    private TextView tvAudienceScore;
    private TextView tvCriticsScore;
    private TextView tvCriticsConsensus;
    private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office_detail);
        ivPosterImage = (ImageView) findViewById(R.id.ivPosterImage);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTrailer = (TextView) findViewById(R.id.tvTrailer);
        tvSynopsis = (TextView) findViewById(R.id.tvSynopsis);
        tvCast = (TextView) findViewById(R.id.tvCast);
        tvCriticsConsensus = (TextView) findViewById(R.id.tvCriticsConsensus);
        tvAudienceScore =  (TextView) findViewById(R.id.tvAudienceScore);
        tvCriticsScore = (TextView) findViewById(R.id.tvCriticsScore);

        // Use the movie to populate the data into our views
        BoxOfficeMovie movie = (BoxOfficeMovie)
                getIntent().getSerializableExtra(BoxOfficeActivity.MOVIE_DETAIL_KEY);
        loadMovie(movie);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_movie);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    // Populate the data for the movie
    public void loadMovie(BoxOfficeMovie movie) {
        // Populate data
        tvTitle.setText(Html.fromHtml("<b></b> " + (movie.getTitle())));
        tvTrailer.setText(Html.fromHtml("<b>Official Trailer:</b> " + (movie.getTitle())));
        tvCriticsScore.setText(Html.fromHtml("<b>Critics Score:</b> " + movie.getCriticsScore() + "%"));
        tvAudienceScore.setText(Html.fromHtml("<b>Audience Score:</b> " + movie.getAudienceScore() + "%"));
        tvCast.setText(Html.fromHtml("<b>Cast:</b> " + (movie.getCastList())));
        tvSynopsis.setText(Html.fromHtml("<b>Synopsis:</b> " + movie.getSynopsis()));
        tvCriticsConsensus.setText(Html.fromHtml("<b></b> " + movie.getCriticsConsensus()));
        // R.drawable.large_movie_poster from
        // http://content8.flixster.com/movie/11/15/86/11158674_pro.jpg -->
        Picasso.with(this).load(movie.getLargePosterUrl()).
                placeholder(R.drawable.large_movie_poster).
                into(ivPosterImage);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_box_office_detail, menu);

        // Set up ShareActionProvider's default share intent
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);
        mShareActionProvider.setShareIntent(getDefaultIntent());

        return super.onCreateOptionsMenu(menu);
       /* shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,"text to share");
        mShare.setShareIntent(shareIntent);
        return true;*/
    }
    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "text to share");
        mShareActionProvider.setShareIntent(intent);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
