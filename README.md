# githubtrend
The project's idea is to list trending projects from Github and then tap on one of them and show their details.

## Solution decription
*  Clean Architecture patron has been implemented using MVVM → the view (fragment) observe a StateFlow object which feeds states to the view that reacts to them and update the UI  
* For networking calls has been used `okHttp` and `retrofit` libraries. As well `coroutines` have been used to make the asynchronous request.

* `Coil` was the library chosen as image loading library, I have worked with other famous libraries like `Picasso` and `glide` but for sometime I’ve been using Coil  because it is developed in `Kotlin` and used coroutines, the performance it’s quite good.

* To fetch github trending repositories it has been used a [copy](https://github-trending-api.waningflow.com) of: [https://github.com/huchenme/github-trending-api](https://github.com/huchenme/github-trending-api), because this is [no longer alive](https://github.com/huchenme/github-trending-api/issues/130%28https://github.com/huchenme/github-trending-api/issues/130%29) 

* Some UI test and some unitTest have been done.
