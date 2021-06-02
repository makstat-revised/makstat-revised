import React from "react";
import Title from "@components/title";
import Navbar from "@components/navbar";

import { Bar } from "react-chartjs-2";

function About() {
    const [makstatData, setMakstatData] = React.useState([]);

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

    const getArrayOfCounts = (gender) =>
        [].concat.apply(
            [],
            makstatData.map((subCategory) => {
                return [].concat.apply(
                    [],
                    subCategory.years.map((year) => {
                        return year.counts
                            .filter((count) => count.gender === gender)
                            .map((count) => count.count);
                    })
                );
            })
        );

    const data = {
        labels: [].concat.apply(
            [],
            makstatData.map((subCategory) => {
                return subCategory.years.map(
                    (year) => `${subCategory.subCategory} -> ${year.year}`
                );
            })
        ),

        datasets: [
            {
                label: "female",
                data: getArrayOfCounts("female"),
                backgroundColor: "rgb(255, 99, 132)",
            },
            {
                label: "male",
                data: getArrayOfCounts("male"),
                backgroundColor: "rgb(54, 162, 235)",
            },
        ],
    };

    const options = {
        scales: {
            yAxes: [
                {
                    stacked: true,
                    ticks: {
                        beginAtZero: true,
                    },
                },
            ],
            xAxes: [
                {
                    stacked: true,
                },
            ],
        },
    };

    return (
        <div>
            <Title />
            <Navbar page={"about"} />
            <div>
                <h3>About us</h3>
                <Bar data={data} options={options} />
            </div>
        </div>
    );
}

export default About;
