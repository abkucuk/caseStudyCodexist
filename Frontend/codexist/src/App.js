import { Button, Checkbox, Form, Input } from 'antd';
import React from 'react';
import axios from 'axios';
import { Wrapper, Status } from "@googlemaps/react-wrapper";

const App = () => {
  let data = '';
  const onFinish = (values) => {
    console.log('Success:', values);
    axios
      .post(`http://localhost:8070/api/getNearbyPlaces?latitude=${values.latitude}&longitude=${values.longitude}&radius=${values.radius}`)
      .then(result => {
        data = result;
        console.log('result', result);
      })
  };

  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  const render = (status) => {
    return <h1>{status}</h1>;
  };

  const ref = React.useRef(null);
  const [map, setMap] = React.useState();

  React.useEffect(() => {
    if (ref.current && !map) {
      setMap(new window.google.maps.Map(ref.current, {}));
    }
  }, [ref, map]);



  return (
    <div className={"container"}>
      <div className="align-items-center justify-content-center" style={{marginTop:'10vh'}}>

        <Form
          name="basic"
          labelCol={{
            span: 8,
          }}
          wrapperCol={{
            span: 4,
          }}
          initialValues={{
            remember: true,
          }}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          autoComplete="off"
        >
          <Form.Item
            label="longitude"
            name="longitude"
            rules={[
              {
                required: true,
                message: 'longitude is required',
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="latitude"
            name="latitude"
            rules={[
              {
                required: true,
                message: 'latitude is required!',
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="radius"
            name="radius"
            rules={[
              {
                required: true,
                message: 'radius is required',
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            wrapperCol={{
              offset: 8,
              span: 16,
            }}
          >
            <Button type="primary" htmlType="submit" >
              Submit
            </Button>
          </Form.Item>
        </Form>

        <br/>
        {data == '' ? '' : data}
      </div>
    </div>
  );
};

export default App;
