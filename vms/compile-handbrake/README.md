This vagrant box came out of needing to use a newer version of x264 in Handbrake than what I was currently
getting from Ubuntu. The older version kept giving me segmentation faults on certain videos I was trying
to encode. Since Handbrake bakes in x264, I had to compile the whole thing to get a newer x264. There were
many bumps along the way, so I built this box so the process was repeatable. It takes some time when first
started (20-30 minutes excluding initial image download), but when it's finished, you'll have a freshly
compiled Handbrake from the latest code on Github.

Builds everything successfully as of Feb 2016.
