import { SmallTitle } from '../Basic/Title/Title';

const TotalPrice = (props) => {
	const totalPrice = `$${props.totalPrice.toFixed(2)}`
	return <SmallTitle>{totalPrice}</SmallTitle>
}

export default TotalPrice;