CursorLoader-Builder
====================

A builder factory for Android CursorLoaders

This is not production level code - only something I playing around with for a personal app.

I find that constructing CursorLoaders is painful and results in some ugly code that is barely readable.  CursorLoader-Build aims to fix that at least a little bit.

Here is what a typical CursorLoader definition looks like:

```java
@Override
public Loader<Cursor> onCreateLoader(int id, Bundle args) { 
    final String[] projection = { BaseColumns._ID, ModelContract.Models.COLUMN_NAME_MODEL,
            ModelContract.Models.COLUMN_NAME_NICE_NAME };
 
    new CursorLoader(getActivity(), ModelContract.Models.CONTENT_URI, projection,
            ModelContract.Models.COLUMN_NAME_MAKE_NAME + "= ?", new String[] {"Jeep"},
            ModelContract.Models.COLUMN_NAME_MODEL + " ASC");
}
```

and here's the same thing with CursorLoader-Builder:

```java
@Override
public Loader<Cursor> onCreateLoader(int id, Bundle args) { 
  CursorLoader modelsForMakeCursorLoader = new Builder(getActivity())
    .uri(ModelContract.Models.CONTENT_URI)
    .addProjectionColumn(BaseColumns._ID)
    .addProjectionColumn(ModelContract.Models.COLUMN_NAME_MAKE_NAME)
    .addProjectionColumn(ModelContract.Models.COLUMN_NAME_MODEL)
    .select(
        new SelectPart.Builder()
            .column(ModelContract.Models.COLUMN_NAME_MAKE_NAME)
            .equals("Jeep").build() 
        )
    .sort(ModelContract.Models.COLUMN_NAME_MODEL, Sort.ASC)
    .build();
    
    return modelsForMakeCursorLoader;
}
```

... it's more code, but hopefully its way easier to read (and maintain).

License
=======

    Copyright 2013 Nicholas Pike

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
