/*!

=========================================================
* Black Dashboard React v1.2.0
=========================================================

* Product Page: https://www.creative-tim.com/product/black-dashboard-react
* Copyright 2020 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/black-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React, {useEffect, useState} from "react";
// nodejs library that concatenates classes
//import classNames from "classnames";
// react plugin used to create charts
import { Line, Bar } from "react-chartjs-2";
import classNames from 'classnames';
// import BarSimple from '../variables/chart1';

// reactstrap components
import {
  Button,
  ButtonGroup,
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  UncontrolledDropdown,
  Label,
  FormGroup,
  Input,
  Table,
  Row,
  Col,
  UncontrolledTooltip,
} from "reactstrap";

// core components
import {
  chartExample1,
  chartExample2,
  chartExample3,
  chartExample4,
} from "../variables/charts";
import Example from "../variables/charts2";
// import data from "../../../api_mock/employeeCount/employeeCount.json"  

// const EmployeeCountData=data.employeeCount;


function Dashboard(props) {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [items, setItems] = useState([]);
  const [bigChartData, setbigChartData] = React.useState("data1");
  const setBgChartData = (name) => {
    setbigChartData(name);
  };
  const [lineAgriCulture, setLineAgriculture]= useState(undefined);
  const [dataChart, setDataChart] = useState(undefined);
  const getData =()=>{
   
  }
  console.log("items",items);
  useEffect(() => {
    fetch("http://localhost:8080/employeeCount/")
    .then(res => res.json())
    .then(
      (result) => { debugger;
      
        setIsLoaded(true);
        console.log('result',result.categories._embedded.categories);
        setItems(result.categories._embedded.categories); 
        let res = result.categories._embedded.categories;
        setDataChart([{
          name: res? `${res[0].subCategories._embedded.subCategories[0].years._embedded.years[0].year}`:'kaltrina',
          male: res? `${res[0].subCategories._embedded.subCategories[0].years._embedded.years[0].genders._embedded.genders[0].count}`: 'kaltrina',
          female: res? `${res[0].subCategories._embedded.subCategories[0].years._embedded.years[0].genders._embedded.genders[1].count}`: 'kaltrina',
          amt: 2400,
        },
        {
          name: res? `${res[0].subCategories._embedded.subCategories[0].years._embedded.years[1].year}`:'kaltrina',
          male: res? `${res[0].subCategories._embedded.subCategories[0].years._embedded.years[1].genders._embedded.genders[0].count}`: 'kaltrina',
          female: res? `${res[0].subCategories._embedded.subCategories[0].years._embedded.years[1].genders._embedded.genders[1].count}`: 'kaltrina',
          amt: 2210,
        },
        ])      
       
        if(res.length!==0){
          let chartE1 = {
            data1: (canvas) => {
              let ctx = canvas.getContext("2d");
          
              let gradientStroke = ctx.createLinearGradient(0, 230, 0, 50);
          
              gradientStroke.addColorStop(1, "rgba(29,140,248,0.2)");
              gradientStroke.addColorStop(0.4, "rgba(29,140,248,0.0)");
              gradientStroke.addColorStop(0, "rgba(29,140,248,0)"); //blue colors
          
              return {
                labels: [
                  res? `${res[0].subCategories._embedded.subCategories[0].subCategory}`:"kaltrina",
                  // items? items.years._embedded.years[0].genders._embedded.genders[0].gender: "",
                  res? `${res[0].subCategories._embedded.subCategories[1].subCategory}`:"kaltrina",
                //  res? `${res[0].subCategories._embedded.subCategories[2].subCategory}`:"kaltrina",
                  // "MAY",
                  // "JUN",
                  // "JUL",
                  // "AUG",
                  // "SEP",
                  // "OCT",
                  // "NOV",
                  // "DEC",
                ],
                datasets: [
                  {
                    label: "My First dataset",
                    fill: true,
                    backgroundColor: gradientStroke,
                    borderColor: "#1f8ef1",
                    borderWidth: 2,
                    borderDash: [],
                    borderDashOffset: 0.0,
                    pointBackgroundColor: "#1f8ef1",
                    pointBorderColor: "rgba(255,255,255,0)",
                    pointHoverBackgroundColor: "#1f8ef1",
                    pointBorderWidth: 20,
                    pointHoverRadius: 4,
                    pointHoverBorderWidth: 15,
                    pointRadius: 4,
                    data: [100, 70]//90]//, 70, 85, 60, 75, 60, 90, 80, 110, 100],
                  },
                ],
              };
            },
          }
          setLineAgriculture(chartE1);
          console.log("chartE1", chartE1);
        }   
      },
      (error) => {
        setIsLoaded(true);
        setError(error);
      }
    )
    
  }, []);

  
// const data = [
//   {
//     name: 'Page A',
//     uv: 4000,
//     pv: 2400,
//     amt: 2400,
//   },
//   {
//     name: 'Page B',
//     uv: 3000,
//     pv: 1398,
//     amt: 2210,
//   },
//   {
//     name: 'Page C',
//     uv: 2000,
//     pv: 9800,
//     amt: 2290,
//   },
//   {
//     name: 'Page D',
//     uv: 2780,
//     pv: 3908,
//     amt: 2000,
//   },
//   {
//     name: 'Page E',
//     uv: 1890,
//     pv: 4800,
//     amt: 2181,
//   },
//   {
//     name: 'Page F',
//     uv: 2390,
//     pv: 3800,
//     amt: 2500,
//   },
//   {
//     name: 'Page G',
//     uv: 3490,
//     pv: 4300,
//     amt: 2100,
//   },
// ];

     
  {
  return (
    <>
      <div className="content">
        
        {/* {dataChart && <Example/>
        } */}
        <Row>
          <Col xs="12">
            <Card className="card-chart">
              <CardHeader>
                <Row>
                  <Col className="text-left" sm="6">
                    <h4 className="card-category">Employee Count </h4>
                    {/* <BarSimple /> */}
                    <Example data={dataChart}/>
                    <CardTitle tag="h2">Performance</CardTitle>
                  </Col>
                  <Col sm="6">
                    <ButtonGroup
                      className="btn-group-toggle float-right"
                      data-toggle="buttons"
                    >
                      <Button
                        tag="label"
                        className={classNames("btn-simple", {
                          active: bigChartData === "data1",
                        })}
                        color="info"
                        id="0"
                        size="sm"
                        onClick={() => setBgChartData("data1")}
                      >
                        <span className="d-none d-sm-block d-md-block d-lg-block d-xl-block">
                          Accounts
                        </span>
                        <span className="d-block d-sm-none">
                          <i className="tim-icons icon-single-02" />
                        </span>
                      </Button>
                      <Button
                        color="info"
                        id="1"
                        size="sm"
                        tag="label"
                        className={classNames("btn-simple", {
                          active: bigChartData === "data2",
                        })}
                        onClick={() => setBgChartData("data2")}
                      >
                        <span className="d-none d-sm-block d-md-block d-lg-block d-xl-block">
                          Purchases
                        </span>
                        <span className="d-block d-sm-none">
                          <i className="tim-icons icon-gift-2" />
                        </span>
                      </Button>
                      <Button
                        color="info"
                        id="2"
                        size="sm"
                        tag="label"
                        className={classNames("btn-simple", {
                          active: bigChartData === "data3",
                        })}
                        onClick={() => setBgChartData("data3")}
                      >
                        <span className="d-none d-sm-block d-md-block d-lg-block d-xl-block">
                          Sessions
                        </span>
                        <span className="d-block d-sm-none">
                          <i className="tim-icons icon-tap-02" />
                        </span>
                      </Button>
                    </ButtonGroup>
                  </Col>
                </Row>
              </CardHeader>
              <CardBody>
               {lineAgriCulture && <div className="chart-area">
                  <Line
                    data={lineAgriCulture.data1}
                    options={lineAgriCulture.options}
                  />
                </div> }
              </CardBody>
            </Card>
          </Col>
        </Row>
        <Row>
          <Col lg="4">
            <Card className="card-chart">
              <CardHeader>
                <h5 className="card-category">Total Shipments</h5>
                <CardTitle tag="h3">
                  <i className="tim-icons icon-bell-55 text-info" /> 763,215
                </CardTitle>
              </CardHeader>
              <CardBody>
                <div className="chart-area">
                  <Line
                    data={chartExample2.data}
                    options={chartExample2.options}
                  />
                </div>
              </CardBody>
            </Card>
          </Col>
          <Col lg="4">
            <Card className="card-chart">
              <CardHeader>
                <h5 className="card-category">Daily Sales</h5>
                <CardTitle tag="h3">
                  <i className="tim-icons icon-delivery-fast text-primary" />{" "}
                  3,500€
                </CardTitle>
              </CardHeader>
              <CardBody>
                <div className="chart-area">
                  <Bar
                    data={chartExample3.data}
                    options={chartExample3.options}
                  />
                </div>
              </CardBody>
            </Card>
          </Col>
               </Row>
      </div>
    </>
  );
}};

export default Dashboard;
