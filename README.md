# BeautifulGraphSample
This library is inspired from [Zane David](https://dribbble.com/zane_david)'s Animation Transition Work on Dribbble.

<img src="images/screenshot.png" width="320"/>

# How Do I use this library?
Include the <b>beautifulgraph</b> library module inside your build.gradle.

Include this in your layout.xml file

```
<com.deepakbaliga.beautifulgraph.Plotter
        android:id="@+id/plotter_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

And in your java file - 

```java

    private Plotter plotter;
    private List<Integer> plots =  new ArrayList<>();

    plotter = (Plotter) findViewById(R.id.plotter_view);
        plotter.setRowCol(10,10);
        plotter.setPlots(plots);
  
```

Do not forget to call the ```.setRowCol()``` and ```.setPlots()``` 

# License  

MIT License

Copyright (c) 2016 Deepak Baliga

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
