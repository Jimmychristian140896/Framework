package framework.jimmy.com.framework.activity;


import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.jaychang.srv.OnLoadMoreListener;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleRecyclerView;
import com.jaychang.srv.behavior.DragAndDropCallback;
import com.jaychang.srv.behavior.SwipeDirection;
import com.jaychang.srv.behavior.SwipeToDismissCallback;
import com.jaychang.srv.decoration.SectionHeaderProvider;
import com.jaychang.srv.decoration.SimpleSectionHeaderProvider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.model.Category;
import framework.jimmy.com.framework.model.Galaxy;
import framework.jimmy.com.framework.model.GalaxyCell;
import framework.jimmy.com.framework.model.NotificationOvo;

public class GalaxyActivity extends AppCompatActivity {
    private static final DateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
    SimpleRecyclerView simpleRecyclerView;

    /*
    - When activity is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaxy);

        simpleRecyclerView = findViewById(R.id.recyclerView);
        this.addRecyclerHeaders();
        this.bindData();
        loadMore();
        refresh();
    }

    private void refresh() {
        simpleRecyclerView.enableDragAndDrop(new DragAndDropCallback() {
            @Override
            public boolean enableDefaultRaiseEffect() {
                return super.enableDefaultRaiseEffect();
            }

            @Override
            public void onCellDragStarted(@NonNull SimpleRecyclerView simpleRecyclerView, @NonNull View itemView, @NonNull Object item, int position) {
                super.onCellDragStarted(simpleRecyclerView, itemView, item, position);
            }

            @Override
            public void onCellMoved(@NonNull SimpleRecyclerView simpleRecyclerView, @NonNull View itemView, @NonNull Object item, int fromPosition, int toPosition) {
                super.onCellMoved(simpleRecyclerView, itemView, item, fromPosition, toPosition);
            }

            @Override
            public void onCellDropped(@NonNull SimpleRecyclerView simpleRecyclerView, @NonNull View itemView, @NonNull Object item, int initialPosition, int toPosition) {
                super.onCellDropped(simpleRecyclerView, itemView, item, initialPosition, toPosition);
            }

            @Override
            public void onCellDragCancelled(@NonNull SimpleRecyclerView simpleRecyclerView, @NonNull View itemView, @NonNull Object item, int currentPosition) {
                super.onCellDragCancelled(simpleRecyclerView, itemView, item, currentPosition);
            }
        });
        simpleRecyclerView.enableSwipeToDismiss(new SwipeToDismissCallback() {
            @Override
            public boolean enableDefaultFadeOutEffect() {
                return super.enableDefaultFadeOutEffect();
            }

            @Override
            public void onCellSwiping(@NonNull SimpleRecyclerView simpleRecyclerView, @NonNull View itemView, @NonNull Object item, int position, @NonNull Canvas canvas, float dX, float dY, boolean isControlledByUser) {
                super.onCellSwiping(simpleRecyclerView, itemView, item, position, canvas, dX, dY, isControlledByUser);
            }

            @Override
            public void onCellSettled(@NonNull SimpleRecyclerView simpleRecyclerView, @NonNull View itemView, @NonNull Object item, int position) {
                super.onCellSettled(simpleRecyclerView, itemView, item, position);
            }

            @Override
            public void onCellDismissed(@NonNull SimpleRecyclerView simpleRecyclerView, @NonNull Object item, int position) {
                super.onCellDismissed(simpleRecyclerView, item, position);
            }
        }, SwipeDirection.LEFT, SwipeDirection.RIGHT);
    }

    private void loadMore() {

        simpleRecyclerView.setAutoLoadMoreThreshold(4);
        simpleRecyclerView.setLoadMoreView(R.layout.load_more);
        simpleRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public boolean shouldLoadMore() {
                return true;
            }

            @Override
            public void onLoadMore() {

                simpleRecyclerView.showLoadMoreView();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        simpleRecyclerView.hideLoadMoreView();
                        ArrayList<NotificationOvo> listNotificationOvoDetail = new ArrayList<>();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(2018, 7, 20);
                        // Required empty public constructor
                        NotificationOvo notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
                        //listNotificationOvoDetail.add(notificationOvo);
                        GalaxyCell cell = new GalaxyCell(notificationOvo);
                        simpleRecyclerView.addCell(cell);
                        simpleRecyclerView.addCell(cell);
                        simpleRecyclerView.addCell(cell);
                        simpleRecyclerView.addCell(cell);
                        simpleRecyclerView.addCell(cell);
                        simpleRecyclerView.addCell(cell);
                        simpleRecyclerView.addCell(cell);
                        simpleRecyclerView.addCell(cell);
                        simpleRecyclerView.addCell(cell);
                        simpleRecyclerView.addCell(cell);
                    }
                }, 5000);
            }
        });

    }


    /*
    - Create RecyclerViewHeaders
     */
    private void addRecyclerHeaders() {
        SectionHeaderProvider<NotificationOvo> sh = new SimpleSectionHeaderProvider<NotificationOvo>() {
            @NonNull
            @Override
            public View getSectionHeaderView(@NonNull NotificationOvo Galaxy, int i) {
                View view = LayoutInflater.from(GalaxyActivity.this).inflate(R.layout.header, null, false);
                TextView textView = view.findViewById(R.id.txt_notification_date);
                textView.setText(sdf.format(Galaxy.getDate().getTime()).toUpperCase());
                return view;
            }

            @Override
            public boolean isSameSection(@NonNull NotificationOvo Galaxy, @NonNull NotificationOvo nextGalaxy) {
                return Galaxy.getDate().getTime().getDate() == nextGalaxy.getDate().getTime().getDate();
            }

            // Optional, whether the header is sticky, default false
            @Override
            public boolean isSticky() {
                return true;
            }
        };
        simpleRecyclerView.setSectionHeader(sh);
    }

    /*
    - Bind data to our RecyclerView
     */
    private void bindData() {
        List<NotificationOvo> Galaxys = getData();
        //CUSTOM SORT ACCORDING TO CATEGORIES
        Collections.sort(Galaxys, new Comparator<NotificationOvo>() {
            public int compare(NotificationOvo Galaxy, NotificationOvo nextGalaxy) {
                return Galaxy.getDate().compareTo(nextGalaxy.getDate());
            }
        });
        List<GalaxyCell> cells = new ArrayList<>();

        //LOOP THROUGH GALAXIES INSTANTIATING THEIR CELLS AND ADDING TO CELLS COLLECTION
        for (NotificationOvo galaxy : Galaxys) {
            GalaxyCell cell = new GalaxyCell(galaxy);
            // There are two default cell listeners: OnCellClickListener<CELL, VH, T> and OnCellLongClickListener<CELL, VH, T>
            cell.setOnCellClickListener(new SimpleCell.OnCellClickListener<NotificationOvo>() {
                @Override
                public void onCellClicked(@NonNull NotificationOvo item) {
                    Toast.makeText(GalaxyActivity.this, item.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            cell.setOnCellLongClickListener(new SimpleCell.OnCellLongClickListener<NotificationOvo>() {
                @Override
                public void onCellLongClicked(@NonNull NotificationOvo item) {
                    Toast.makeText(GalaxyActivity.this, item.getReference(), Toast.LENGTH_SHORT).show();
                }
            });


            cells.add(cell);
        }
        simpleRecyclerView.addCells(cells);
    }

    /*
    - Data Source
    - Returns an arraylist of galaxies.
     */
    private ArrayList<NotificationOvo> getData() {


        ArrayList<NotificationOvo> listNotificationOvoDetail = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 7, 17);
        // Required empty public constructor
        NotificationOvo notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);

        notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);

        notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);
        notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);
        notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);
        notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);
        notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);
        notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);
        notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);
        notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);
        notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);
        notificationOvo = new NotificationOvo(calendar, "Your Payment Rp100 in Informa is cancelled. Yout OVO Points balance is updated", false, "false");
        listNotificationOvoDetail.add(notificationOvo);


        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2018, 7, 18);
        NotificationOvo notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);

        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);
        notificationOvo1 = new NotificationOvo(calendar2, "Top Up Rp1.000.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo1);

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2018, 7, 19);
        NotificationOvo notificationOvo2 = new NotificationOvo(calendar2, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        notificationOvo2 = new NotificationOvo(calendar3, "Top Up Rp100.000 dari OVO Project Management", false, "true");
        listNotificationOvoDetail.add(notificationOvo2);

        return listNotificationOvoDetail;


        /*//CREATE CATEGORIES
        Category elliptical=new Category(0,"Elliptical");
        Category irregular=new Category(1,"Irregular");
        Category spiral=new Category(2,"Spiral");

        //INSTANTIATE GALAXY OBJECTS AND ADD THEM TO GALAXY LIST
        Galaxy g=new Galaxy("Whirlpool",
                "The Whirlpool Galaxy, also known as Messier 51a, M51a, and NGC 5194, is an interacting grand-design spiral Galaxy with a Seyfert 2 active galactic nucleus in the constellation Canes Venatici.",
                R.drawable.whirlpool,spiral);
        galaxies.add(g);

        g=new Galaxy("Ring Nebular",
                "The Ring Nebula is a planetary nebula in the northern constellation of Lyra. Such objects are formed when a shell of ionized gas is expelled into the surrounding interstellar medium by a red giant star.",
                R.drawable.ringnebular,elliptical);
        galaxies.add(g);

        g=new Galaxy("IC 1011",
                "C 1011 is a compact elliptical galaxy with apparent magnitude of 14.7, and with a redshift of z=0.02564 or 0.025703, yielding a distance of 100 to 120 Megaparsecs. Its light has taken 349.5 million years to travel to Earth.",
                R.drawable.ic1011,elliptical);
        galaxies.add(g);



        g=new Galaxy("Triangulumn",
                "The Triangulum Galaxy is a spiral Galaxy approximately 3 million light-years from Earth in the constellation Triangulum",
                R.drawable.triangulum,spiral);
        galaxies.add(g);

        g=new Galaxy("Small Magellonic Cloud",
                "The Small Magellanic Cloud, or Nubecula Minor, is a dwarf galaxy near the Milky Way. It is classified as a dwarf irregular galaxy.",
                R.drawable.smallamgellonic,irregular);
        galaxies.add(g);

        g=new Galaxy("Centaurus A",
                " Centaurus A or NGC 5128 is a galaxy in the constellation of Centaurus. It was discovered in 1826 by Scottish astronomer James Dunlop from his home in Parramatta, in New South Wales, Australia.",
                R.drawable.centaurusa,elliptical);
        galaxies.add(g);

        g=new Galaxy("Ursa Minor",
                "The Milky Way is the Galaxy that contains our Solar System." +
                        " The descriptive milky is derived from the appearance from Earth of the Galaxy – a band of light seen in the night sky formed from stars",
                R.drawable.ursaminor,irregular);
        galaxies.add(g);

        g=new Galaxy("Large Magellonic Cloud",
                " The Large Magellanic Cloud is a satellite galaxy of the Milky Way. At a distance of 50 kiloparsecs, the LMC is the third-closest galaxy to the Milky Way, after the Sagittarius Dwarf Spheroidal and the.",
                R.drawable.largemagellonic,irregular);
        galaxies.add(g);

        g=new Galaxy("Milky Way",
                "The Milky Way is the Galaxy that contains our Solar System." +
                        " The descriptive milky is derived from the appearance from Earth of the Galaxy – a band of light seen in the night sky formed from stars",
                R.drawable.milkyway,spiral);
        galaxies.add(g);

        g=new Galaxy("Andromeda",
                "The Andromeda Galaxy, also known as Messier 31, M31, or NGC 224, is a spiral Galaxy approximately 780 kiloparsecs from Earth. It is the nearest major Galaxy to the Milky Way and was often referred to as the Great Andromeda Nebula in older texts.",
                R.drawable.andromeda,irregular);
        galaxies.add(g);

        g=new Galaxy("Messier 81",
                "Messier 81 is a spiral Galaxy about 12 million light-years away in the constellation Ursa Major. Due to its proximity to Earth, large size and active galactic nucleus, Messier 81 has been studied extensively by professional astronomers.",
                R.drawable.messier81,elliptical);
        galaxies.add(g);

        g=new Galaxy("Own Nebular",
                " The Owl Nebula is a planetary nebula located approximately 2,030 light years away in the constellation Ursa Major. It was discovered by French astronomer Pierre Méchain on February 16, 1781",
                R.drawable.ownnebular,elliptical);
        galaxies.add(g);

        g=new Galaxy("Messier 87",
                "Messier 87 is a supergiant elliptical galaxy in the constellation Virgo. One of the most massive galaxies in the local universe, it is notable for its large population of globular clusters—M87 contains",
                R.drawable.messier87,elliptical);
        galaxies.add(g);

        g=new Galaxy("Cosmos Redshift",
                "Cosmos Redshift 7 is a high-redshift Lyman-alpha emitter Galaxy, in the constellation Sextans, about 12.9 billion light travel distance years from Earth, reported to contain the first stars —formed ",
                R.drawable.cosmosredshift,irregular);
        galaxies.add(g);

        g=new Galaxy("StarBust",
                "A starburst Galaxy is a Galaxy undergoing an exceptionally high rate of star formation, as compared to the long-term average rate of star formation in the Galaxy or the star formation rate observed in most other galaxies. ",
                R.drawable.starbust,irregular);
        galaxies.add(g);

        g=new Galaxy("Sombrero",
                "Sombrero Galaxy is an unbarred spiral galaxy in the constellation Virgo located 31 million light-years from Earth. The galaxy has a diameter of approximately 50,000 light-years, 30% the size of the Milky Way.",
                R.drawable.sombrero,spiral);
        galaxies.add(g);

        g=new Galaxy("Pinwheel",
                "The Pinwheel Galaxy is a face-on spiral galaxy distanced 21 million light-years away from earth in the constellation Ursa Major. ",
                R.drawable.pinwheel,spiral);
        galaxies.add(g);


        g=new Galaxy("Canis Majos Overdensity",
                "The Canis Major Dwarf Galaxy or Canis Major Overdensity is a disputed dwarf irregular galaxy in the Local Group, located in the same part of the sky as the constellation Canis Major. ",
                R.drawable.canismajoroverdensity,irregular);
        galaxies.add(g);

        g=new Galaxy("Virgo Stella Stream",
                " Group, located in the same part of the sky as the constellation Canis Major. ",
                R.drawable.virgostellarstream,spiral);
        galaxies.add(g);

        return galaxies;*/
    }

}