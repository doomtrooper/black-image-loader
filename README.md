# black-image-loader

An light-weight android module to fetch images from Internet and load into respective views.The view must be a imageview or one of its sub-class.

  - Type some Markdown on the left
  - See HTML in the right
  - Magic

### Version
1.0.0

### Tech
Its a thread-safe module with Singleton architecture.It uses a thread-pool to sumit the task to fetch images from internet and LRUcache to cache the fetched bitmaps to future uses. It can also be uses to set the resouces from drawables directly into the imagevies.

### Installation

It will soon be available on jCenter for usage.



### Development

Want to contribute? Great!

Black-image-loader requires basic understanding of java development.

### Usage

```sh
Black.init(context).loadIntoImageview(imageView,url); //basic usage
```
To set the placeholder image while fetching bitmap from network.
```sh
Black.init(context).placeholder(R.drawable.image).loadIntoImageview(imageView,url);
```
To set the resource after there has been any error while fetching the image from network.
```sh
Black.init(context).loadIntoImageview(imageView,url).error(R.drawable.error_image);
```
At the same time the logging ofinformation can be switched with the following function.
```sh
Black.init(context).log(boolean);
```

License
----

MIT


**Free Software, Hell Yeah!**


