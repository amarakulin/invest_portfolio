const initializeApp = (props) => {
	props.getSettedCategory();
	props.getCategories();
	props.getTotalAssets();
	props.getTotalPrice();
}

export default initializeApp;