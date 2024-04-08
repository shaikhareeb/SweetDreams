# Project Title: SweetDreams

# Project Proposal Link

https://git.uwaterloo.ca/r2baxi/Team-102-16/-/wikis/Project-Proposal

# Project Description

This project intends to provide a convenient, one-stop solution for parents to upload and explore lullabies for their children, and for children to play their parents' selected lullabies. This tool is made for parents and their young children, developed for a CS 346 project in Winter 2024. This app differes from other similar applications by adding an explore page linking lullabies from Youtube and publicly posted ones from other users. Core users will include parents who want to help their children sleep but are unavailable (busy, out of town, etc.).

# Essential Product Features

Platform: Desktop

1. Account feature to allow users to have their own accounts
3. Support multi-user scenarios through the accounts feature **(Advanced feature)**
4. Explore page for inspiration lullabies
5. Explore page includes lullabies from Youtube via Youtube application and can be watched when clicked for inspiration **(Advanced feature)**
6. Upload a lullaby audio (intended for parents)
7. Upload thumbnails to pair with the audios (intended for parents and can be personlized images)
8. View and listen to uploaded lullabies in the app (intended for children)
9. Play audio in background as users can use other apps.
10. Add a child lock preventing app functionality that can only be unlocked via parental password.
11. Play lullabies automatically once the queue is started.
12. Lullabies stop playing after the user stops playing the queue
13. Lullabies can be put into a queue and played and will continue to play after lock screen is initiated. 
14. Deployed private user data to the cloud including account information, personal audios and thumbnails, and queues **(Advanced feature)**

# Team Members

1. Rohun Baxi - r2baxi@uwaterloo.ca
2. Akshen Jasikumar - ajasikum@uwaterloo.ca
3. Yun Tao - y83tao@uwaterloo.ca
4. Areeb Shaikh - a59shaik@uwaterloo.ca

# Screenshots

Screenshots - https://git.uwaterloo.ca/r2baxi/Team-102-16/-/wikis/Screenshots

# User Documentation

To run this application, clone the repo, ensure JDK 17 is installed, and press run on the top right corner of the IDE.

Usage Documentation - https://git.uwaterloo.ca/r2baxi/Team-102-16/-/wikis/User-Documentation

# Design Documentation

Design Docs - https://git.uwaterloo.ca/r2baxi/Team-102-16/-/wikis/Design-Documents

# Software Releases

1. Sprint 1 Release (Feb. 16, 2024): https://git.uwaterloo.ca/r2baxi/Team-102-16/-/releases/sprint-1-conclusion
2. Sprint 2 Release (Mar. 8, 2024): https://git.uwaterloo.ca/r2baxi/Team-102-16/-/releases/sprint-2-conclusion
3. Sprint 3 Release (Mar. 22, 2024): https://git.uwaterloo.ca/r2baxi/Team-102-16/-/releases/sprint-3-conclusion
4. Sprint 4 Release (Apr. 5, 2024): https://git.uwaterloo.ca/r2baxi/Team-102-16/-/releases/sprint-4-conclusion

# Meeting Minutes and Individual Developer Journals

Meeting Minutes: https://git.uwaterloo.ca/r2baxi/Team-102-16/-/wikis/Team-Meeting-Minutes

Developer Journals:

1. Rohun Baxi - https://git.uwaterloo.ca/r2baxi/Team-102-16/-/wikis/Rohun-Baxi-Development-Journal
2. Akshen Jasikumar - https://git.uwaterloo.ca/r2baxi/Team-102-16/-/wikis/Akshen-Jasikumar-Dev-Journal
3. Yun Tao - https://git.uwaterloo.ca/r2baxi/Team-102-16/-/wikis/Dev-Journal-Yun-Tao
4. Areeb Shaikh - https://git.uwaterloo.ca/r2baxi/Team-102-16/-/wikis/Areeb-Shaikh%E2%80%99s-Development-Journal


# Third-Party Acknowledgements / Citations

A citation for a small snippet of code inspired from a 3rd party online tutorial external code source (https://www.geeksforgeeks.org/java-swing-jfilechooser/) is included in UploadPage.kt.

A citation for a small snippet of code used for the basic setup of a audio stream was taken from this Kotlin forums link (https://slack-chats.kotlinlang.org/t/520128/is-there-an-api-to-play-audio-using-compose-desktop-i-got-an) is included in AudioManager.kt

A citation for the general scaffolding structure used for the pages of this project from this StackOverflow link (https://stackoverflow.com/questions/72081206/kotlin-jetpack-compose-how-to-remove-dp-or-sizefrom-the-modifier-fillmaxheight) is included in UserPage.kt

A citation for opening links in Desktop browsers as per this StackOverflow link (https://stackoverflow.com/questions/68306576/open-a-link-in-browser-using-compose-for-desktop) is included in PlaylistsPage.kt and ExplorePage.kt

A citation for deleting audio files via removing blobs from GCP from this StackOverflow link (https://stackoverflow.com/questions/53657627/deleting-multiple-blobs-from-google-cloud-storage-efficiently) is included in UploadManager.kt 

Certain emojis and unicode values used in the source code are from https://www.iemoji.com/.

Externals tools include Youtube API and its documentation, Firebase and its documentation, and Google Cloud and its documentation.

# Copyright & License

