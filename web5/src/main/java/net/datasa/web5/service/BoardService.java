package net.datasa.web5.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.datasa.web5.domain.dto.BoardDTO;
import net.datasa.web5.domain.entity.BoardEntity;
import net.datasa.web5.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardrepository;
	
	public void save(BoardDTO dto, AuthenticatedUser user) {
		// TODO Auto-generated method stub
		
		String memberId = user.getUsername();
		
		BoardEntity entity = BoardEntity.builder()
				.boardNum(dto.getBoardNum())
				.memberId(memberId)
				.title(dto.getTitle())
				.contents(dto.getContents())
				.veiwCount(dto.getVeiwCount())
				.likeCount(dto.getLikeCount())
				.originalName(dto.getOriginalName())
				.fileName(dto.getFileName())
				.createDate(dto.getCreateDate())
				.updateDate(dto.getUpdateDate())
				.build();
				
		boardrepository.save(entity);
	}

}
