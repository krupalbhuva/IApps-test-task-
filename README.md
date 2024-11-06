# Test task 

## Requirements

- Kotlin as the primary programming language.
- Utilization of Google's architecture components: MVVM and Repositories.
- Data fetching and caching for offline availability. Data should be reloaded on each app start.
- Layouts implemented in XML (not Jetpack Compose).
- Display data in a vertical RecyclerView list, with one column on smartphones and three columns on larger screens.
- Each item in the list should display its image with a description underneath.
- Clicking on an item should open the link associated with the photo in an external browser.
- Data should be sorted by the published field.

## Implementation

- Room Database
- Retrofit
- Hilt (Dependency Injection)

### Data Source

The application fetches data from the Flickr public photo feed API:
[https://api.flickr.com/services/feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1]