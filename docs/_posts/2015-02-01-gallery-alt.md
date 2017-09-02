---
author: someone
layout: post-full
type: gallery
featimg: gallery-2.jpg
title: Gallery Alternative
tags: [gallery, image]
category: [image]
gallery:
    - images:
      - filename: gallery-1.jpg
        alttext: Bloom Flat
      - filename: gallery-2.jpg
        alttext: Bloom
      - filename: gallery-3.jpg
        alttext: Blossom in a Star
      - filename: gallery-4.jpg
        alttext: Blossom
      - filename: gallery-5.jpg
        alttext: Bubbly Bloom
      - filename: gallery-6.jpg
        alttext: Rays of Gold
      - filename: gallery-7.jpg
        alttext: Exotic
      - filename: gallery-8.jpg
        alttext: Filled out
---

An alternative to the simple gallery would be this version, which displays the post ins a lightbox.
The setup basically is the same, the include makes all the difference.

Galleries are defined in a data-sheet, set type and gallery-id in front matter and include `gallery_lightbox.html` within the content.
<br>

###### front matter

```yml
---
layout: post-full
type: gallery
gallery:
    - images:
      - filename: gallery-1.jpg
        alttext: Bloom Flat
      - filename: gallery-2.jpg
        alttext: Bloom
      - filename: gallery-3.jpg
        alttext: Blossom in a Star
      - filename: gallery-4.jpg
        alttext: Blossom
      - filename: gallery-5.jpg
        alttext: Bubbly Bloom
      - filename: gallery-6.jpg
        alttext: Rays of Gold
      - filename: gallery-7.jpg
        alttext: Exotic
      - filename: gallery-8.jpg
        alttext: Filled out
---
```
<br>

###### post content

``` liquid
{% raw  %}
{% include gallery_lightbox.html %}
{% endraw %}
```

{% include gallery_lightbox.html %}