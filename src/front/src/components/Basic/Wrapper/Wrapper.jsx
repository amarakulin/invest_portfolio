import styled from 'styled-components';

export const Wrapper = styled.div`
	display: flex;
	align-items: center;
	justify-content: space-between;
	${props => props.marginBottom && `margin-bottom: ${props.marginBottom}px`}
`;

