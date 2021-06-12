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
import React from "react";

// reactstrap components
import {
  Button,
  ButtonGroup,
  CardTitle,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  CardText,
  FormGroup,
  Form,
  Input,
  Row,
  Col,
} from "reactstrap";
import classNames from "classnames";
import { Bar, Line } from "react-chartjs-2";
import { array } from "prop-types";
function EmploymentTimeOfCovid() {
    const [makstatData, setMakstatData] = React.useState([]);
    const [bigChartData, setbigChartData] = React.useState("data1");
    const setBgChartData = (name) => {
      setbigChartData(name);
    };
    const[subCategory,setSubcategory]= React.useState("agriculture");

    React.useEffect(() => {
        const fetchData = async () => {
            const category = "sector";

            try {
                const subCategoriesResult = await fetch(
                    `http://localhost:8080/employeeCount/${category}`
                );
                const subCategoriesData = await subCategoriesResult.json();
                const subCategories =
                    subCategoriesData.subCategories._embedded.subCategories.map(
                        (subCategory) => subCategory.subCategory
                    );

                console.log("subCategories", subCategories);

                const subCategoriesWithYearsAndGenders = await Promise.all(
                    subCategories.map(async (subCategory) => {
                        const yearsResult = await fetch(
                            `http://localhost:8080/employeeCount/${category}/${subCategory}`
                        );
                        const yearsData = await yearsResult.json();
                        const years = yearsData.years._embedded.years.map(
                            (year) => year.year
                        );

                        const yearsWithGenders = await Promise.all(
                            years.map(async (year) => {
                                const gendersResult = await fetch(
                                    `http://localhost:8080/employeeCount/${category}/${subCategory}/${year}`
                                );
                                const gendersData = await gendersResult.json();
                                const genders =
                                    gendersData.genders._embedded.genders.map(
                                        (gender) => {
                                            return {
                                                gender: gender.gender,
                                                count: gender.count,
                                            };
                                        }
                                    );
                                return {
                                    year,
                                    counts: genders,
                                };
                            })
                        );
                        console.log('yearsWithGenders',yearsWithGenders);

                        return {
                            subCategory,
                            years: yearsWithGenders,
                        };
                    })
                );

                console.log(
                    "subCategoriesWithYearsAndGenders",
                    subCategoriesWithYearsAndGenders
                );

                setMakstatData(subCategoriesWithYearsAndGenders);
            } catch (error) {
                console.log(error);
            }
        };

        fetchData();
    }, []);

    const getArrayOfCounts = (gender, subCat) =>{
      let tempArr=[];
        [].concat.apply(
            [],
            makstatData.map((subCategory) => {
                if(subCategory.subCategory===subCat)
                {
                    console.log("subcategory", subCategory.subCategory);
                    return [].concat.apply(
                        [],
                        subCategory.years.map((year) => 
                        { console.log(year.counts);
                          //if(year.year>2010)
                          //{
                            return year.counts
                                .filter((count) => count.gender === gender)
                                .map((count) =>{
                                  tempArr.push(count.count);
                                   return count.count})
                          //}
                        })
                    );
                }        
            })
        );
        return tempArr;
      }

    // const getSubcategories=()=>{
    //   [].concat.apply(
    //     [],
    //     makstatData.map((subCategory)=>{
    //       return subCategory.subCategory;
    //     })
    //   )
    // }
    const getYearsForSubcategory=(subCat)=>
    {    
    let yearsForSubcategoryArray=[];
      [].concat.apply(
        [],
        makstatData.map((subCategory) => {
          if(subCategory.subCategory===subCat)
          {
            return subCategory.years.map(
                (year) => {yearsForSubcategoryArray.push(`${subCategory.subCategory} -> ${year.year}`)}
            );
          }
        }),         
      );
      return yearsForSubcategoryArray;
    }

    let data={};
  const getData=(subCat)=>{
    return (
    data = {
      data: (canvas) => {
        let ctx = canvas.getContext("2d");
    
        let gradientStroke = ctx.createLinearGradient(0, 230, 0, 50);
    
        gradientStroke.addColorStop(1, "rgba(72,72,176,0.1)");
        gradientStroke.addColorStop(0.4, "rgba(72,72,176,0.0)");
        gradientStroke.addColorStop(0, "rgba(119,52,169,0)"); //purple colors
    
        return {   
          labels: getYearsForSubcategory("agriculture"),
          datasets: [
            {
              label: "female",
              fill: true,
              backgroundColor: gradientStroke,
              hoverBackgroundColor: gradientStroke,
              borderColor: "#d048b6",
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              data: getArrayOfCounts("female",subCat),
            },
            {
              label: "Male",
              fill: true,
              backgroundColor: gradientStroke,
              hoverBackgroundColor: gradientStroke,
              borderColor: "#1000bb",
              borderWidth: 2,
              borderDash: [],
              borderDashOffset: 0.0,
              data: getArrayOfCounts("male",subCat),
            },
          ],
        };
      },
    }
  )};  
    const options={
      options: {
        maintainAspectRatio: false,
        legend: {
          display: false,
        },
        tooltips: {
          backgroundColor: "#f5f5f5",
          titleFontColor: "#333",
          bodyFontColor: "#666",
          bodySpacing: 4,
          xPadding: 12,
          mode: "nearest",
          intersect: 0,
          position: "nearest",
        },
        responsive: true,
        scales: {
          yAxes: [
            {
              stacked:false,
              gridLines: {
                drawBorder: false,
                color: "rgba(225,78,202,0.1)",
                zeroLineColor: "transparent",
              },
              ticks: {
                suggestedMin: 60,
                suggestedMax: 120,
                padding: 20,
                fontColor: "#9e9e9e",
                beginAtZero: true,
              },
            },
          ],
          xAxes: [
            {         
              
              stacked: false,
              gridLines: {
                drawBorder: false,
                color: "rgba(225,78,202,0.1)",
                zeroLineColor: "transparent",
              },
              ticks: {
                padding: 20,
                fontColor: "#9e9e9e",
              },
            },
          ],
        },
      },
    };
// {debugger;
//     return (
//         <div className="content">
//         <Row>
//           <Col xs="12">
//             <Card className="card-chart">
//               <CardHeader>
//                 <Row>
//                   <Col className="text-left" sm="6">
//                     <h5 className="card-category">Number of employment selected in categories, devided by gender</h5>
//                     <CardTitle tag="h2">Employment</CardTitle>
//                   </Col>
//                   <Col sm="6">
//                     <ButtonGroup
//                       className="btn-group-toggle float-right"
//                       data-toggle="buttons"
//                     >
  
//                   { makstatData.map((subCategory)=>{
//                       return(
//                         <Button
//                         tag="label"
//                         className={classNames("btn-simple", {
//                           active: subCategory === subCategory.subCategory,
//                         })}
//                         color="info"
//                         id="0"
//                         size="sm"
//                         onClick={() => setSubcategory(subCategory.subCategory)}
//                       >
//                         <span className="d-none d-sm-block d-md-block d-lg-block d-xl-block">
//                         {subCategory.subCategory}
//                         </span>
//                         <span className="d-block d-sm-none">
//                           <i className="tim-icons icon-single-02" />
//                         </span>
//                       </Button>
//                       );
//                     })}
//                     </ButtonGroup>
//                   </Col>
//                 </Row>
//               </CardHeader>
//               <CardBody>
//                 <div className="chart-area">
//                   {/* <Line
//                     data={chartExample1[bigChartData]}
//                     options={chartExample1.options}
//                   /> */}
//                   <div style={{ position: "relative", margin: "auto", display: "block", height: "199px",  width: "399px"}}>
//                 <Bar data={getData(subCategory).data} options={options} />
//                 </div>
//                 {/* <Bar data={chartExample3.data} options={chartExample3.options} /> */}
//                 </div>
//               </CardBody>
//             </Card>
//           </Col>
//         </Row>
//         <Row>
//           <Col xs="12">
//             <Card className="card-chart">
//               <CardHeader>
//                 <Row>
//                   <Col className="text-left" sm="6">
//                     <h5 className="card-category">Number of employment selected in categories, devided by gender</h5>
//                     <CardTitle tag="h2">Employment</CardTitle>
//                   </Col>
//                   <Col sm="6">
//                     <ButtonGroup
//                       className="btn-group-toggle float-right"
//                       data-toggle="buttons"
//                     >
//                   { makstatData.map((subCategory)=>{
//                       return(
//                         <Button
//                         tag="label"
//                         className={classNames("btn-simple", {
//                           active: subCategory === subCategory.subCategory,
//                         })}
//                         color="info"
//                         id="0"
//                         size="sm"
//                         onClick={() => setSubcategory(subCategory.subCategory)}
//                       >
//                         <span className="d-none d-sm-block d-md-block d-lg-block d-xl-block">
//                         {subCategory.subCategory}
//                         </span>
//                         <span className="d-block d-sm-none">
//                           <i className="tim-icons icon-single-02" />
//                         </span>
//                       </Button>
//                       );
//                     })}
//                     </ButtonGroup>
//                   </Col>
//                 </Row>
//               </CardHeader>
//               <CardBody>
//                 <div className="chart-area">
//                   {/* <Line
//                     data={chartExample1[bigChartData]}
//                     options={chartExample1.options}
//                   /> */}
//                   <div style={{ position: "relative", margin: "auto", display: "block", height: "199px",  width: "399px"}}>
//                 <Line data={getData(subCategory).data} options={options} />
//                 </div>
//                 {/* <Bar data={chartExample3.data} options={chartExample3.options} /> */}
//                 </div>
//               </CardBody>
//             </Card>
//           </Col>
//         </Row>
//         </div>
//     );
// }}


  return (
    <>
      <div className="content">
        <Row>
          <Col md="8">
            {/* <Card>
              <CardHeader>
                <h5 className="title">Edit Profile</h5>
              </CardHeader>
              <CardBody>
                <Form>
                  <Row>
                    <Col className="pr-md-1" md="5">
                      <FormGroup>
                        <label>Company (disabled)</label>
                        <Input
                          defaultValue="Creative Code Inc."
                          disabled
                          placeholder="Company"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                    <Col className="px-md-1" md="3">
                      <FormGroup>
                        <label>Username</label>
                        <Input
                          defaultValue="michael23"
                          placeholder="Username"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                    <Col className="pl-md-1" md="4">
                      <FormGroup>
                        <label htmlFor="exampleInputEmail1">
                          Email address
                        </label>
                        <Input placeholder="mike@email.com" type="email" />
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pr-md-1" md="6">
                      <FormGroup>
                        <label>First Name</label>
                        <Input
                          defaultValue="Mike"
                          placeholder="Company"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                    <Col className="pl-md-1" md="6">
                      <FormGroup>
                        <label>Last Name</label>
                        <Input
                          defaultValue="Andrew"
                          placeholder="Last Name"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="12">
                      <FormGroup>
                        <label>Address</label>
                        <Input
                          defaultValue="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"
                          placeholder="Home Address"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pr-md-1" md="4">
                      <FormGroup>
                        <label>City</label>
                        <Input
                          defaultValue="Mike"
                          placeholder="City"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                    <Col className="px-md-1" md="4">
                      <FormGroup>
                        <label>Country</label>
                        <Input
                          defaultValue="Andrew"
                          placeholder="Country"
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                    <Col className="pl-md-1" md="4">
                      <FormGroup>
                        <label>Postal Code</label>
                        <Input placeholder="ZIP Code" type="number" />
                      </FormGroup>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="8">
                      <FormGroup>
                        <label>About Me</label>
                        <Input
                          cols="80"
                          defaultValue="Lamborghini Mercy, Your chick she so thirsty, I'm in
                            that two seat Lambo."
                          placeholder="Here can be your description"
                          rows="4"
                          type="textarea"
                        />
                      </FormGroup>
                    </Col>
                  </Row>
                </Form>
              </CardBody>
              <CardFooter>
                <Button className="btn-fill" color="primary" type="submit">
                  Save
                </Button>
              </CardFooter>
            </Card> */}
            <Card className="card-chart">
              <CardHeader>
                <Row>
                  <Col className="text-left" sm="6">
                    <h5 className="card-category">Number of employment selected in categories, devided by gender</h5>
                    <CardTitle tag="h2">Employment</CardTitle>
                  </Col>
                  <Col sm="6">
                    <ButtonGroup
                      className="btn-group-toggle float-right"
                      data-toggle="buttons"
                    >
                  { makstatData.map((subCategory)=>{
                      return(
                        <Button
                        tag="label"
                        className={classNames("btn-simple", {
                          active: subCategory === subCategory.subCategory,
                        })}
                        color="info"
                        id="0"
                        size="sm"
                        onClick={() => setSubcategory(subCategory.subCategory)}
                      >
                        <span className="d-none d-sm-block d-md-block d-lg-block d-xl-block">
                        {subCategory.subCategory}
                        </span>
                        <span className="d-block d-sm-none">
                          <i className="tim-icons icon-single-02" />
                        </span>
                      </Button>
                      );
                    })}
                    </ButtonGroup>
                  </Col>
                </Row>
              </CardHeader>
              <CardBody>
                <div className="chart-area">
                  {/* <Line
                    data={chartExample1[bigChartData]}
                    options={chartExample1.options}
                  /> */}
                  <div style={{ position: "relative", margin: "auto", display: "block", height: "300px",  width: "399px"}}>
                <Line data={getData(subCategory).data} options={options} />
                </div>
                {/* <Bar data={chartExample3.data} options={chartExample3.options} /> */}
                </div>
              </CardBody>
            </Card>
          </Col>
          <Col md="4">
            <Card className="card-user">
              <CardBody>
                <CardText />
                <div className="author">
                  <div className="block block-one" />
                  <div className="block block-two" />
                  <div className="block block-three" />
                  <div className="block block-four" />
                  
                </div>
                <h3> Employment and wages Report 2020-21: Wages and minimum wages in the time of COVID-19</h3>
                <div className="card-description">
               <p>
               The COVID-19 pandemic has transformed into an unprecedented global economic and labour market crisis, hurting millions of workers and enterprises. While the impact on jobs has been widely documented, the effects on wages are less known.   
              </p>

<p>What have been the pandemic’s impacts on workers’ wages? What key measures have governments and social partners taken to protect workers’ wages? How have minimum wages evolved, both before and during the crisis?
</p>

<p>We invite you to explore the findings of the 2020-21 edition of the ILO’s Global Wage Report in this InfoStory. The InfoStory will be updated regularly as new statistical data are collected.
</p>
            </div>
              </CardBody>
              <CardFooter>
               
              </CardFooter>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default EmploymentTimeOfCovid;
