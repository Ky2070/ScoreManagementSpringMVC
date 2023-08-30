import React from 'react';
import { Carousel } from 'react-bootstrap';

const ExampleCarouselImage = ({ text }) => {

    const imageStyle = {
        width: '100%', // Điều chỉnh chiều rộng hình ảnh
        height: '500px', // Để duy trì tỷ lệ khung hình
      };
  return (
    <Carousel>
      <Carousel.Item>
        <img
          style={imageStyle}
          src="https://img.lovepik.com/photo/20211210/medium/lovepik-campus-playground-picture_501784050.jpg"
          alt={text}
        />
      </Carousel.Item>
      <Carousel.Item>
        <img
          style={imageStyle}
          src="https://png.pngtree.com/background/20230608/original/pngtree-the-courtyard-of-stanford-university-picture-image_2904161.jpg"
          alt={text}
        />
      </Carousel.Item>
      <Carousel.Item>
        <img
          style={imageStyle}
          src="https://kenh14cdn.com/thumb_w/660/2019/9/8/15-1567938316558392570707.jpg"
          alt={text}
        />
      </Carousel.Item>
    </Carousel>
  );
};

export default ExampleCarouselImage;