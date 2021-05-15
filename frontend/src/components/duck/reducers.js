import constants from "./constants"

const defaultState = {
    items: undefined,
    category: undefined,
}

export default function reducer( currentSate = defaultState, action) {
    switch(actions.type) {
        case constants.GET_ITEMS_SUCCESS:
            return {
                ...currentSate,
                items: action.payload,
            }
        default: 
            return currentSate;
    }
}