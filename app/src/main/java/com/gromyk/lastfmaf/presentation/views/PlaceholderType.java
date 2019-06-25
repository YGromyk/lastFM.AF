package com.gromyk.lastfmaf.presentation.views;

import androidx.annotation.IntDef;

@IntDef({
        PlaceholderType.NO_INTERNET,
        PlaceholderType.NO_ARTISTS,
        PlaceholderType.NO_ALBUMS,
        PlaceholderType.NO_SONGS,
        PlaceholderType.SEARCH
})
public @interface PlaceholderType {
    int NO_INTERNET = 1;
    int NO_ARTISTS = 2;
    int NO_ALBUMS = 3;
    int NO_SONGS = 4;
    int SEARCH = 5;
}
