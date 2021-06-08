import styled from 'styled-components';

export const Wrapper = styled.div`
	display: flex;
	align-items: center;
	justify-content: space-between;
	${props => props.marginBottom && `margin-bottom: ${props.marginBottom}px`}
`;

export const StartWrapper = styled(Wrapper)`
	justify-content: start;
`

export const Container = styled.div`
	width: 75%;
	margin: 0 auto;
	max-width: 1440px;
`

