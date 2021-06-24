import styled from 'styled-components';
import Legend from './Legend/Legend'
import Svg from './Svg/Svg'

const DiargammCanvas = styled.div`
	display: flex;
	justify-content: space-around;
	align-items: center;
	margin-top: 85px;
`

const Diagramm = (props) => {
	return (
		<DiargammCanvas>
			<Svg
				{...props}
			/>
			<Legend
				{...props}
			/>
		</DiargammCanvas>
	)
}

export default Diagramm;