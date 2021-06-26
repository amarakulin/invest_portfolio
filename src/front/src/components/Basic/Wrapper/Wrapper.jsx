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

export const CenterWrapper = styled(Wrapper)`
	justify-content: center;
`

export const AlignEndWrapper = styled(Wrapper)`
	align-items: flex-end;
`

export const Container = styled.div`
	width: 75%;
	margin: 0 auto;
	max-width: 1440px;
`

export const GridWrapper = styled.div`
	display: grid;
	gap: 20px 10px;
	grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
	margin-bottom: 40px;
`
