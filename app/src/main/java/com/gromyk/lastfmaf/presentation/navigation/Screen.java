package com.gromyk.lastfmaf.presentation.navigation;

import androidx.annotation.IntDef;

@IntDef({Screen.ALBUM_DETAILS, Screen.ARTIST_DETAILS, Screen.OPEN_WEB})
public  @interface Screen {
    int ALBUM_DETAILS = 0;
    int ARTIST_DETAILS = 1;
    int OPEN_WEB = 2;
}