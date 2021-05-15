import actions from "./actions";

const getAllItems = (items) => {
    return (dispatch) => {
        dispatch(actions.getItemsRequest());
        return fetch("http://localhost:8080/employeeCount")
            .then(res => {
                dispatch(actions.getItemsSuccess(res));
                return Promise.resolve(res);
            })
            .catch(e => {
                dispatch(actions.getItemsFail(e));
                Promise.reject(e);
            })
             
    }
}

export default { getAllItems }