# MYSIC Full Code
The code is messy and inconsistent (mix of hardcode + dynamic code + different implementations of similar stuff)
as I was learning while coding.

## Info:
1) Main nav pages are achieved using Fragments,
however other pages are Activities as fragments was too tough for me.
2) Light/Dark Mode is persistent. Device's own mode may affect it.
3) Online Songs are taken from YouTube and uploaded to Discord to optimize fetching speeds. Initially sourced songs from ncs.io, but the fetching speed was too slow.
4) Offline/Downloaded Songs are 30 Seconds, from Spotify's API.

## Known Bugs:
1) Playlist crashes devices like Pixel 4
2) Search indexes incorrectly - brings to wrong songs when clicked
